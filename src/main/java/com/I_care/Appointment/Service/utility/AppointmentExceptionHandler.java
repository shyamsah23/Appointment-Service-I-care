package com.I_care.Appointment.Service.utility;

import com.I_care.Appointment.Service.exception.AppointmentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppointmentExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(Exception e) {
        ErrorInfo errorInfo = new ErrorInfo(e.getMessage(), (long) HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ErrorInfo> handleAppointmentException(AppointmentException e) {
        ErrorInfo errorInfo = new ErrorInfo(e.getMessage(), (long) HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
