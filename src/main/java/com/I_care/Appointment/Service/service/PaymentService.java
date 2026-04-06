package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.PaymentVerifyDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;

public interface PaymentService {
    void verifyAndProcess(PaymentVerifyDTO request) throws AppointmentException;
}
