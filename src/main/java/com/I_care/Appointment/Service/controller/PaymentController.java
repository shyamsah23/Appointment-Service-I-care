package com.I_care.Appointment.Service.controller;

import com.I_care.Appointment.Service.dto.PaymentVerifyDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify-and-process")
    public ResponseEntity<String> verifyAndProcess(@RequestBody PaymentVerifyDTO request) throws AppointmentException {
        paymentService.verifyAndProcess(request);
        return ResponseEntity.ok("Payment verified and processed successfully");
    }
}
