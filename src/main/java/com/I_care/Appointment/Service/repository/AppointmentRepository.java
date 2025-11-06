package com.I_care.Appointment.Service.repository;

import com.I_care.Appointment.Service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
