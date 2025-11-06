package com.I_care.Appointment.Service.repository;

import com.I_care.Appointment.Service.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByAppointment_Id(Long appointmentId);
//    List<Prescription> findAllByPatient_Id(Long patientId);
}
