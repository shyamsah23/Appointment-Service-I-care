package com.I_care.Appointment.Service.repository;

import com.I_care.Appointment.Service.dto.AppointmentDetails;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorIdAndAppointmentDateAndStatus(
            Long doctorId, LocalDate date, Status status
    );

    boolean existsByDoctorIdAndAppointmentDateAndStartTimeAndStatus(
            Long doctorId, LocalDate date, LocalTime startTime, Status status
    );

    long countByDoctorIdAndPatientIdAndAppointmentDateAndStatus(
            Long doctorId, Long patientId, LocalDate date, Status status
    );

    List<Appointment> findByStatus(Status status);
}
