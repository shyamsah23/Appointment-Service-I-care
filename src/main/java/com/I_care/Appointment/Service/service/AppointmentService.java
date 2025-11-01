package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.AppointmentDTO;
import com.I_care.Appointment.Service.dto.AppointmentDetails;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.exception.AppointmentException;

import java.time.LocalDateTime;

public interface AppointmentService {

    Long scheduleAppointment(AppointmentDTO appointmentDTO) throws AppointmentException;

    Appointment cancelAppointment(Long appointmentId, String reason) throws AppointmentException;

    void completeAppointment(Long appointmentId);

    Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newDateAndTime, String reasonForReschedule) throws AppointmentException;

    AppointmentDTO getAppointmentDetails(Long appointmentId) throws AppointmentException;

    Boolean publishMessage(String message);

    AppointmentDetails getAppointmentDetailsWithName(Long appointmentId) throws AppointmentException;
}
