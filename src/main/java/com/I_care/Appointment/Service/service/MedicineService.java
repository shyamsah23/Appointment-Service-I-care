package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.MedicineDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;

import java.util.List;

public interface MedicineService {
    public Long saveMedicine(MedicineDTO medicineDTO);

    public List<MedicineDTO> saveAllMedicines(List<MedicineDTO> ListOfMedicines);

    public List<MedicineDTO> getMedicinesByPrescriptionId(Long prescriptionId);
}
