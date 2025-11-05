package com.I_care.Appointment.Service.repository;

import com.I_care.Appointment.Service.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
  List<Medicine> findAllByPrescription_Id(Long prescriptionId);
}
