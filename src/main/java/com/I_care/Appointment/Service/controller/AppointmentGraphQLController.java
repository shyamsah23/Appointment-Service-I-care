package com.I_care.Appointment.Service.controller;

import com.I_care.Appointment.Service.dto.AppointmentDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AppointmentGraphQLController {

    Logger logger = LoggerFactory.getLogger(AppointmentGraphQLController.class);

    @Autowired
    private AppointmentService appointmentService;

    @QueryMapping
    public AppointmentDTO getAppointmentById(@Argument Long id) throws AppointmentException {
        logger.info("GraphQL Call -> Fetching appointment Details for id = {}",id);
        AppointmentDTO appointmentDetails = appointmentService.getAppointmentDetails(id);
        logger.info("Appointment fetched Successfully for appointment Id = {}",id);
        return appointmentDetails;
    }
}
