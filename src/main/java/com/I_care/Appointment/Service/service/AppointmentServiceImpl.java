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

import java.time.LocalDateTime;

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
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) throws AppointmentException {
        logger.info("Checks being performed for provided Doctor & patient with ids={},{} respectively", appointmentDTO.getDoctorId(), appointmentDTO.getPatientId());
        Boolean doctorExists = profileFeignClient.doctorExists(appointmentDTO.getDoctorId());
        if (doctorExists == null || !doctorExists) {
            logger.info("Given doctor doesn't exist in our system with id={}", appointmentDTO.getDoctorId());
            throw new AppointmentException(AppointmentConstant.DOCTOR_NOT_FOUND);
        }
        Boolean patientExists = profileFeignClient.patientExists(appointmentDTO.getPatientId());
        if (patientExists == null || !patientExists) {
            logger.info("Given patient doesn't exist in our system with id={}", appointmentDTO.getPatientId());
            throw new AppointmentException(AppointmentConstant.PATIENT_NOT_FOUND);
        }

        logger.info("Checks performed! Schedule Appointment Started for Patient Id= {}", appointmentDTO.getPatientId());
        Long appointmentId = appointmentRepository.save(appointmentDTO.toEntity()).getId();
        logger.info("Appointment Schedule Successfully for Patient Id= {} and appointment Id= {}", appointmentDTO.getPatientId(), appointmentId);
        logger.info("Preparing data to send Email to patient");

        PatientDTO patientInfo = profileFeignClient.getPatientById(appointmentDTO.getPatientId());
        logger.info("Patient Info Fetched with Patient id = {}", patientInfo.getId());
        EmailDTO emailInfo = notificationServiceHelper.getNotificationDetails(appointmentDTO.getPatientId(), NotificationConstant.APPOINTMENT_BOOKED,
                patientInfo.getEmail(), NotificationConstant.SCHEDULE_APPOINTMENT_SUBJECT);
        // To-Do : Use kafka to Send Mail
        logger.info("Started Sending mail To Patient");
        notificationFeignClient.sendMail(emailInfo);
        logger.info("Successfully Sent the mail");
        return appointmentId;
    }

    @Override
    public Appointment cancelAppointment(Long appointmentId, String reasonForCancellation) throws AppointmentException {
        logger.info("Started Cancelling the Appointment for appointment id= {}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        if (appointment.getStatus().equals(Status.CANCEL)) {
            throw new AppointmentException(AppointmentConstant.APPOINTMENT_ALREADY_CANCELLED);
        }
        appointment.setReason(reasonForCancellation);
        appointment.setStatus(Status.CANCEL);
        logger.info("Successfully Cancelled the appointment ->  Started Triggering Mail");

        // To-Do : Use kafka to Send Mails
        PatientDTO patientInfo = profileFeignClient.getPatientById(appointment.getPatientId());
        logger.info("Patient Info Fetched with Patient id = {}", patientInfo.getId());
        EmailDTO emailInfo = notificationServiceHelper.getNotificationDetails(appointment.getPatientId(),
                NotificationConstant.APPOINTMENT_CANCELLED, patientInfo.getEmail(), NotificationConstant.CANCEL_APPOINTMENT_SUBJECT);

        logger.info("Started Sending mail To Patient");
        notificationFeignClient.sendMail(emailInfo);
        logger.info("Successfully Sended the mail");

        return appointment;
    }

    @Override
    public void completeAppointment(Long appointmentId) {

    }

    @Override
    public Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newDateAndTime, String reasonForReschedule) throws AppointmentException {
        logger.info("Started Rescheduling Appointment for AppointmenId = {}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() ->
                new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        appointment.setReason(reasonForReschedule);
        appointment.setAppointmentDate(newDateAndTime);
        logger.info("Appointment Rescheudle for Appointment Id = {}, to ={}", appointmentId, newDateAndTime);

        // To-Do : Use kafka to Send Mails
        PatientDTO patientInfo = profileFeignClient.getPatientById(appointment.getPatientId());
        EmailDTO emailInfo = notificationServiceHelper.getNotificationDetails(appointment.getPatientId(), NotificationConstant.APPOINTMENT_RESCHEDULED,
                patientInfo.getEmail(), NotificationConstant.APPOINTMENT_RESCHEDULED_SUBJECT);

        logger.info("Started Sending mail To Patient");
        notificationFeignClient.sendMail(emailInfo);
        logger.info("Successfully Sended the mail");

        return appointment;
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
        // Printing Message in case of Failure
//        logger.info("Message = {}",message);
        kafkaTemplate.send(AppointmentConstant.KAFKA_TEST_TOPIC, message);
        logger.info("Message Sent Successfully ");
        return true;
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
        return new AppointmentDetails(appointment.getId(), appointment.getPatientId(), appointment.getDoctorId(), doctorDTO.getName(), patientDTO.getName(), appointment.getAppointmentDate(), appointment.getStatus(), appointment.getReason(), appointment.getNotes());
    }
}
