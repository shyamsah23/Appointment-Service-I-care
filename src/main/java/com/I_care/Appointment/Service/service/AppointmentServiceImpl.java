package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.client.NotificationFeignClient;
import com.I_care.Appointment.Service.client.ProfileFeignClient;
import com.I_care.Appointment.Service.dto.*;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.repository.AppointmentRepository;
import com.I_care.Appointment.Service.utility.AppointmentConstant;
import com.I_care.Appointment.Service.utility.NotificationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private NotificationFeignClient notificationFeignClient;

    @Autowired
    private NotificationServiceHelper notificationServiceHelper;

    @Autowired
    private ProfileFeignClient profileFeignClient;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Transactional
    @Override
    public Long scheduleAppointment(AppointmentDTO dto) throws AppointmentException {

        logger.info("Validating doctor & patient");

        Boolean doctorExists = profileFeignClient.doctorExists(dto.getDoctorId());
        if (doctorExists == null || !doctorExists) {
            throw new AppointmentException(AppointmentConstant.DOCTOR_NOT_FOUND);
        }

        Boolean patientExists = profileFeignClient.patientExists(dto.getPatientId());
        if (patientExists == null || !patientExists) {
            throw new AppointmentException(AppointmentConstant.PATIENT_NOT_FOUND);
        }

        // MAX 2 APPOINTMENTS PER DAY
        long count = appointmentRepository
                .countByDoctorIdAndPatientIdAndAppointmentDateAndStatus(
                        dto.getDoctorId(),
                        dto.getPatientId(),
                        dto.getAppointmentDate(),
                        Status.SCHEDULED
                );

        if (count >= 2) {
            throw new AppointmentException("Max 2 appointments per day allowed");
        }

        //  SLOT ALREADY BOOKED
        boolean exists = appointmentRepository
                .existsByDoctorIdAndAppointmentDateAndStartTimeAndStatus(
                        dto.getDoctorId(),
                        dto.getAppointmentDate(),
                        dto.getStartTime(),
                        Status.SCHEDULED
                );

        if (exists) {
            throw new AppointmentException("Slot already booked");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setPatientId(dto.getPatientId());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());
        appointment.setStatus(Status.SCHEDULED);
        appointment.setReason(dto.getReason());
        appointment.setPaid(true);
        appointment.setRefunded(false);

        appointmentRepository.save(appointment);

        logger.info("Appointment created successfully");

        PatientDTO patientInfo = profileFeignClient.getPatientById(dto.getPatientId());

        EmailDTO emailInfo = notificationServiceHelper.getNotificationDetails(
                dto.getPatientId(),
                NotificationConstant.APPOINTMENT_BOOKED,
                patientInfo.getEmail(),
                NotificationConstant.SCHEDULE_APPOINTMENT_SUBJECT
        );

        notificationFeignClient.sendMail(emailInfo);
        return appointment.getId();
    }

    @Override
    public Appointment cancelAppointment(Long appointmentId, String reason) throws AppointmentException {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Not found"));

        if (appointment.getStatus() == Status.CANCELLED) {
            throw new AppointmentException("Already cancelled");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentTime = LocalDateTime.of(
                appointment.getAppointmentDate(),
                appointment.getStartTime()
        );

        long minutesDiff = Duration.between(now, appointmentTime).toMinutes();

        if (minutesDiff >= 60&& appointment.getPaid() != null && appointment.getPaid()) {
            // REFUND ALLOWED
            appointment.setRefunded(true);
            processRefund(appointment);
        }

        appointment.setStatus(Status.CANCELLED);
        appointment.setReason(reason);

        return appointmentRepository.save(appointment);
    }

    @Override
    public void completeAppointment(Long appointmentId) {

    }

    @Override
    public Appointment rescheduleAppointment(Long appointmentId,
                                             LocalDate date,
                                             LocalTime startTime,
                                             String reason) throws AppointmentException {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));

        LocalTime endTime = startTime.plusMinutes(15);

        //  SLOT CHECK
        boolean exists = appointmentRepository
                .existsByDoctorIdAndAppointmentDateAndStartTimeAndStatus(
                        appointment.getDoctorId(),
                        date,
                        startTime,
                        Status.SCHEDULED
                );

        if (exists) {
            throw new AppointmentException("Slot already booked");
        }

        appointment.setAppointmentDate(date);
        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);
        appointment.setReason(reason);

        return appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDTO getAppointmentDetails(Long appointmentId) throws AppointmentException {
        logger.info("Started Fetching Appointment Details for Appointment Id = {}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        logger.info("Appointment fetched Successfully");
        return appointment.toDTO();
    }

    @Override
    public Boolean publishMessage(String message) {
        logger.info("Stared Sending Message to Topic = {} ", AppointmentConstant.KAFKA_TEST_TOPIC);
        kafkaTemplate.send(AppointmentConstant.KAFKA_TEST_TOPIC, message);
        logger.info("Message Sent Successfully ");
        return true;
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() throws AppointmentException {
        logger.info("Trying to fetch details all appointments");
        return appointmentRepository.findAll().stream()
                .map(Appointment::toDTO).toList();
    }

    @Override
    public AppointmentDetails getAppointmentDetailsWithId(Long appointmentId) throws AppointmentException {
        logger.info("Trying to fetch details for appointment with id={}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        logger.info("Appointment details fetched! Now trying to fetch doctor & patient details based on appointment details");
        DoctorDTO doctorDTO = profileFeignClient.getDoctorById(appointment.getDoctorId());
        logger.info("Doctor details fetched");
        PatientDTO patientDTO = profileFeignClient.getPatientById(appointment.getPatientId());
        logger.info("Patient details fetched! Ready to show data");
        return new AppointmentDetails(appointment.getId(), appointment.getPatientId(), appointment.getDoctorId(), doctorDTO.getName(), patientDTO.getName(), appointment.getAppointmentDate(),appointment.getStartTime(),appointment.getEndTime(), appointment.getStatus(), appointment.getReason(),appointment.getPaid(), appointment.getNotes());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) throws AppointmentException {
        logger.info("Trying to fetch details for appointment with doctorId={}", doctorId);
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(Appointment::toDTO).toList();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) throws AppointmentException {
        logger.info("Trying to fetch details for appointment with patientId={}", patientId);
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(Appointment::toDTO).toList();
    }

    @Override
    public List<SlotDTO> getSlots(Long doctorId, LocalDate date) {

        List<Appointment> booked =
                appointmentRepository.findByDoctorIdAndAppointmentDateAndStatus(
                        doctorId, date, Status.SCHEDULED
                );

        Set<LocalTime> bookedTimes = booked.stream()
                .map(Appointment::getStartTime)
                .collect(Collectors.toSet());

        List<SlotDTO> slots = new ArrayList<>();

        generateSlots(LocalTime.of(9,0), LocalTime.of(13,0), bookedTimes, slots);
        generateSlots(LocalTime.of(17,0), LocalTime.of(21,0), bookedTimes, slots);

        return slots;
    }

    @Override
    public void generateSlots(LocalTime start, LocalTime end,
                              Set<LocalTime> bookedTimes,
                              List<SlotDTO> slots) {

        LocalTime current = start;

        while (current.isBefore(end)) {
            LocalTime next = current.plusMinutes(15);

            SlotDTO s = new SlotDTO();
            s.setStartTime(current);
            s.setEndTime(next);
            s.setBooked(bookedTimes.contains(current));

            slots.add(s);

            current = next;
        }
    }

    private void processRefund(Appointment appointment) {

        if (appointment.getPaymentId() == null) {
            System.out.println("Refund skipped: No paymentId");
            return;
        }

        System.out.println("Refund processed for appointment: " + appointment.getId());

        // TODO: Razorpay refund later

        appointment.setRefunded(true);
    }

}
