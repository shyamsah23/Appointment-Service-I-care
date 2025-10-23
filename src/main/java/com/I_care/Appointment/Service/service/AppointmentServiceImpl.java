package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.AppointmentDTO;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.repository.AppointmentRepository;
import com.I_care.Appointment.Service.utility.AppointmentConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) {
        logger.info("Schedule Appointment Started for Patient Id= {}",appointmentDTO.getPatientId());
        Long appointmentId = appointmentRepository.save(appointmentDTO.toEntity()).getId();
        logger.info("Appointment Schedule Successfully for Patient Id= {} and appointment Id= {}",appointmentDTO.getPatientId(),appointmentId);
        // To- Do : Send Mail for Appointment Scheduling
        return appointmentId;
    }

    @Override
    public Appointment cancelAppointment(Long appointmentId, String reasonForCancellation) throws AppointmentException {
        logger.info("Started Cancelling the Appointment for appointment id= {}", appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        if (appointment.getStatus().equals(Status.CANCEL)) {
            throw new AppointmentException(AppointmentConstant.APPOINTMENT_ALREADY_CANCELLED);
        }
        appointment.setReason(reasonForCancellation);
        appointment.setStatus(Status.CANCEL);
        // TO:Do -> Send Mail
        return appointment;
    }

    @Override
    public void completeAppointment(Long appointmentId) {

    }

    @Override
    public Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newDateAndTime,String reasonForReschedule) throws AppointmentException {
        logger.info("Started Rescheduling Appointment for AppointmenId = {}",appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        appointment.setReason(reasonForReschedule);
        appointment.setAppointmentDate(newDateAndTime);
        logger.info("Appointment Rescheudle for Appointment Id = {}, to ={}",appointmentId,newDateAndTime);
        // To-Do : Send Mail
        return appointment;
    }

    @Override
    public Appointment getAppointmentDetails(Long appointmentId) throws AppointmentException {
        logger.info("Started Fetching Appointment Details for Appointment Id = {}",appointmentId);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_NOT_FOUND));
        logger.info("Appointment fetched Successfully");
        return appointment;
    }
}
