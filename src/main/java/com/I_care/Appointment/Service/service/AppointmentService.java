package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.AppointmentDTO;
import com.I_care.Appointment.Service.dto.AppointmentDetails;
import com.I_care.Appointment.Service.dto.SlotDTO;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.exception.AppointmentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface AppointmentService {

    Long scheduleAppointment(AppointmentDTO appointmentDTO) throws AppointmentException;

    Appointment cancelAppointment(Long appointmentId, String reason) throws AppointmentException;

    void completeAppointment(Long appointmentId);

    Appointment rescheduleAppointment(Long appointmentId, LocalDate date, LocalTime startTime, String reasonForReschedule) throws AppointmentException;

    AppointmentDTO getAppointmentDetails(Long appointmentId) throws AppointmentException;

    Boolean publishMessage(String message);

    List<AppointmentDTO>getAllAppointments() throws AppointmentException;

    AppointmentDetails getAppointmentDetailsWithId(Long appointmentId) throws AppointmentException;

    List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) throws AppointmentException;

    List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) throws AppointmentException;

    List<SlotDTO> getSlots(Long doctorId, LocalDate date) ;

    void generateSlots(LocalTime start, LocalTime end, Set<LocalTime> bookedTimes, List<SlotDTO> slots);
}
