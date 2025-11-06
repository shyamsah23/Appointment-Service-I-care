package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.AppointmentRecordDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;

public interface AppointmentRecordService {
    public Long createAppointmentRecord(AppointmentRecordDTO appointmentRecordDTO) throws AppointmentException;

    public void updateAppointmentRecord(AppointmentRecordDTO appointmentRecordDTO) throws AppointmentException;

    public AppointmentRecordDTO getAppointmentRecordByAppointmentId(Long appointmentId) throws AppointmentException;

    public AppointmentRecordDTO getAppointmentRecordDetailsByAppointmentId(Long appointmentId) throws AppointmentException;

    public AppointmentRecordDTO getAppointmentRecordById(Long recordId) throws AppointmentException;
}
