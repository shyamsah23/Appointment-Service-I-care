package com.I_care.Appointment.Service.controller;

import com.I_care.Appointment.Service.dto.AppointmentRecordDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.service.AppointmentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/appointment-report")
@Validated
public class AppointmentRecordController {
    @Autowired
    AppointmentRecordService appointmentRecordService;

    @PostMapping("/create")
    public ResponseEntity<Long> createAppointmentRecord(@RequestBody AppointmentRecordDTO appointmentRecordDTO) throws AppointmentException {
        return new ResponseEntity<>(appointmentRecordService.createAppointmentRecord(appointmentRecordDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAppointmentRecord(@RequestBody AppointmentRecordDTO appointmentRecordDTO) throws AppointmentException {
        appointmentRecordService.updateAppointmentRecord(appointmentRecordDTO);
        return new ResponseEntity<>("Appointment Record Updated", HttpStatus.OK);
    }

    @GetMapping("/getByAppointmentId/{appointmentId}")
    public ResponseEntity<AppointmentRecordDTO> getAppointmentRecordByAppointmentId(@PathVariable Long appointmentId) throws AppointmentException {
        return new ResponseEntity<>(appointmentRecordService.getAppointmentRecordByAppointmentId(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getByRecordId/{recordId}")
    public ResponseEntity<AppointmentRecordDTO> getAppointmentRecordById(@PathVariable Long recordId) throws AppointmentException {
        return new ResponseEntity<>(appointmentRecordService.getAppointmentRecordById(recordId), HttpStatus.OK);
    }
}
