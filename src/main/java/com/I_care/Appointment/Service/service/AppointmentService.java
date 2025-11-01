package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.AppointmentDTO;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.exception.AppointmentException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentService {

    Long scheduleAppointment(AppointmentDTO appointmentDTO);

    Appointment cancelAppointment(Long appointmentId, String reason) throws AppointmentException;

    void completeAppointment(Long appointmentId);

    Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newDateAndTime,String reasonForReschedule) throws AppointmentException;

    AppointmentDTO getAppointmentDetails(Long appointmentId) throws AppointmentException;

    Boolean publishMessage(String message);
}
