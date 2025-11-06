package com.I_care.Appointment.Service.controller;

import com.I_care.Appointment.Service.dto.AppointmentDTO;
import com.I_care.Appointment.Service.dto.AppointmentDetails;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.service.AppointmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long appointmentId) throws AppointmentException {
        logger.info("Inside Controller -> Started Fetching Appointment Details for Appointment Id = {}", appointmentId);
        AppointmentDTO appointmentInfo = appointmentService.getAppointmentDetails(appointmentId);
        logger.info("Successfully Found Appointment Details");
        return new ResponseEntity<>(appointmentInfo, HttpStatus.OK);
    }

    @PutMapping("/reschedule/{appointmentId}")
    public ResponseEntity<Appointment> rescheduleAppointment(@RequestBody AppointmentDTO appointmentDTO,
                                                             @PathVariable Long appointmentId) throws AppointmentException {
        logger.info("Inside Controller - Started Reschedule Appointment for Appointment Id = {}", appointmentId);
        Appointment updatedAppointment = appointmentService.rescheduleAppointment(appointmentId,
                appointmentDTO.getAppointmentDate(), appointmentDTO.getReason());
        logger.info("Appointment Reschedule Successfully");
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<Appointment> cancelAppointment(@RequestBody AppointmentDTO appointmentDTO,
                                                         @PathVariable Long appointmentId) throws AppointmentException {
        logger.info("Inside Controller - Started Cancelling Appointment for Appointment Id = {}", appointmentId);
        Appointment updatedAppointment = appointmentService.cancelAppointment(appointmentId, appointmentDTO.getReason());
        logger.info("Appointment Cancelled Successfully");
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);

    }

    @PostMapping("/schedule")
    public ResponseEntity<Long> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO) throws AppointmentException {
        logger.info("Inside Controller - Started Scheduling appointment for Patient Id = {} ", appointmentDTO.getPatientId());
        Long appointmentScheduleId = appointmentService.scheduleAppointment(appointmentDTO);
        logger.info("Appointment is Successfully with Appointment Id = {}", appointmentScheduleId);
        return new ResponseEntity<>(appointmentScheduleId, HttpStatus.CREATED);
    }

    @GetMapping("/message/publish/test")
    public Boolean sendMessageToTestTopic() {
        logger.info("Started Publishing the message to the topic -> Inside controller");
        appointmentService.publishMessage("Mail data");
        logger.info("Successfully sended the messgae to the Topic");
        return true;
    }

    @GetMapping("/details/{appointmentId}")
    public ResponseEntity<AppointmentDetails> getAppointmentDetailsWithId(@PathVariable Long appointmentId) throws AppointmentException {
        return new ResponseEntity<>(appointmentService.getAppointmentDetailsWithId(appointmentId), HttpStatus.OK);
    }
}
