package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.MedicineDTO;
import com.I_care.Appointment.Service.entity.Medicine;
import com.I_care.Appointment.Service.repository.MedicineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public Long saveMedicine(MedicineDTO medicineDTO) {
        return medicineRepository.save(medicineDTO.toEntity()).getId();
    }

    @Override
    public List<MedicineDTO> saveAllMedicines(List<MedicineDTO> listOfMedicines) {
        return ((List<Medicine>) medicineRepository.saveAll(listOfMedicines.stream().map(MedicineDTO::toEntity).toList())).stream().map(Medicine::toDTO).toList();
    }

    @Override
    public List<MedicineDTO> getMedicinesByPrescriptionId(Long prescriptionId) {
        return ((List<Medicine>) medicineRepository.findAllByPrescription_Id(prescriptionId)).stream().map(Medicine::toDTO).toList();
    }
}
