package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.PrescriptionDTO;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.repository.PrescriptionRepository;
import com.I_care.Appointment.Service.utility.AppointmentConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService{
    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    MedicineService medicineService;

    @Override
    public Long savePrescription(PrescriptionDTO prescriptionDTO){
        Long prescriptionId=prescriptionRepository.save(prescriptionDTO.toEntity()).getId();
        prescriptionDTO.getMedicines().forEach(medicine->{
            medicine.setPrescriptionId(prescriptionId);
        });
        medicineService.saveAllMedicines(prescriptionDTO.getMedicines());
        return prescriptionId;
    }

    @Override
    public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws AppointmentException{
        PrescriptionDTO prescriptionDTO= prescriptionRepository.findByAppointment_Id(appointmentId).orElseThrow(()->new AppointmentException(AppointmentConstant.PRESCRIPTION_NOT_FOUND)).toDTO();
        return prescriptionDTO;
    }

    @Override
    public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws AppointmentException{
        PrescriptionDTO prescriptionDTO=prescriptionRepository.findById(prescriptionId).orElseThrow(()->new AppointmentException(AppointmentConstant.PRESCRIPTION_NOT_FOUND)).toDTO();
        prescriptionDTO.setMedicines(medicineService.getMedicinesByPrescriptionId(prescriptionDTO.getId()));
        return prescriptionDTO;
    }
}