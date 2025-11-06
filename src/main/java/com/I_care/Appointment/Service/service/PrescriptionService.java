package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.PrescriptionDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;

public interface PrescriptionService {
    public Long savePrescription(PrescriptionDTO prescriptionDTO);

    public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws AppointmentException;

    public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws AppointmentException;
}
