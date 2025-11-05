package com.I_care.Appointment.Service.repository;

import com.I_care.Appointment.Service.entity.AppointmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRecordRepository extends JpaRepository<AppointmentRecord, Long> {
    Optional<AppointmentRecord> findByAppointment_Id(Long appointmentId);

}
