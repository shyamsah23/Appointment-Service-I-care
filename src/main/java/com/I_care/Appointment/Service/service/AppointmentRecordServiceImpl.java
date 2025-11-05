package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.AppointmentRecordDTO;
import com.I_care.Appointment.Service.entity.AppointmentRecord;
import com.I_care.Appointment.Service.exception.AppointmentException;
import com.I_care.Appointment.Service.repository.AppointmentRecordRepository;
import com.I_care.Appointment.Service.utility.AppointmentConstant;
import com.I_care.Appointment.Service.utility.StringListConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AppointmentRecordServiceImpl implements AppointmentRecordService {
    @Autowired
    AppointmentRecordRepository appointmentRecordRepository;

    @Autowired
    PrescriptionService prescriptionService;

    @Override
    public Long createAppointmentRecord(AppointmentRecordDTO request) throws AppointmentException {
        Optional<AppointmentRecord> existingRecord = appointmentRecordRepository.findByAppointment_Id(request.getAppointmentId());
        if (existingRecord.isPresent()) {
            throw new AppointmentException(AppointmentConstant.APPOINTMENT_RECORD_ALREADY_EXISTS);
        }
        Long id = appointmentRecordRepository.save(request.toEntity()).getId();
        if (request.getPrescriptionDTO() != null) {
            request.getPrescriptionDTO().setAppointmentId(request.getAppointmentId());
            prescriptionService.savePrescription(request.getPrescriptionDTO());
        }
        return id;
    }

    @Override
    public void updateAppointmentRecord(AppointmentRecordDTO request) throws AppointmentException {
        AppointmentRecord existingRecord = appointmentRecordRepository.findById(request.getId()).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_RECORD_DOES_NOT_EXIST));
        existingRecord.setNotes(request.getNotes());
        existingRecord.setDiagnosis(request.getDiagnosis());
        existingRecord.setFollowUpDate(request.getFollowUpDate());
        existingRecord.setSymptoms(StringListConverter.convertListToString(request.getSymptoms()));
        existingRecord.setTests(StringListConverter.convertListToString(request.getTests()));
        existingRecord.setReferral(request.getReferral());
        appointmentRecordRepository.save(existingRecord);
    }

    @Override
    public AppointmentRecordDTO getAppointmentRecordByAppointmentId(Long appointmentId) throws AppointmentException {
        return appointmentRecordRepository.findByAppointment_Id(appointmentId).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_RECORD_DOES_NOT_EXIST)).toDTO();
    }

    @Override
    public AppointmentRecordDTO getAppointmentRecordDetailsByAppointmentId(Long appointmentId) throws AppointmentException {
        AppointmentRecordDTO record = appointmentRecordRepository.findByAppointment_Id(appointmentId).orElseThrow(() -> new AppointmentException(AppointmentConstant.APPOINTMENT_RECORD_DOES_NOT_EXIST)).toDTO();
        record.setPrescriptionDTO(prescriptionService.getPrescriptionByAppointmentId(appointmentId));
        return record;
    }

    @Override
    public AppointmentRecordDTO getAppointmentRecordById(Long recordId) throws AppointmentException {
        return appointmentRecordRepository.findById(recordId).orElseThrow(() -> new ArithmeticException(AppointmentConstant.APPOINTMENT_RECORD_DOES_NOT_EXIST)).toDTO();
    }

}
