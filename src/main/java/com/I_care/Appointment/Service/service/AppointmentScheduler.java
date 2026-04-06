package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;
import com.I_care.Appointment.Service.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AppointmentScheduler {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Scheduled(fixedRate = 60000) // every 1 min
    public void markNoShowAppointments() {

        List<Appointment> appointments =
                appointmentRepository.findByStatus(Status.SCHEDULED);

        LocalDateTime now = LocalDateTime.now();

        for (Appointment a : appointments) {

            LocalDateTime appointmentTime = LocalDateTime.of(
                    a.getAppointmentDate(),
                    a.getStartTime()
            );

            if (appointmentTime.plusMinutes(15).isBefore(now)) {
                a.setStatus(Status.NO_SHOW);
                appointmentRepository.save(a);
            }
        }
    }
}
