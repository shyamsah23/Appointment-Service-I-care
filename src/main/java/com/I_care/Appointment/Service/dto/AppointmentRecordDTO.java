package com.I_care.Appointment.Service.dto;

import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.entity.AppointmentRecord;
import com.I_care.Appointment.Service.utility.StringListConverter;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRecordDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private List<String> symptoms;
    private String diagnosis;
    private List<String> tests;
    private String notes;
    private String referral;
    private PrescriptionDTO prescriptionDTO;
    private LocalDateTime followUpDate;
    private LocalDateTime createdAt;

    public AppointmentRecordDTO(Long appointmentId, Long id, Long patientId, Long doctorId, List<String> symptoms, String diagnosis, List<String> tests, String notes, String referral, PrescriptionDTO prescriptionDTO, LocalDateTime followUpDate, LocalDateTime createdAt) {
        this.appointmentId = appointmentId;
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.tests = tests;
        this.notes = notes;
        this.referral = referral;
        this.prescriptionDTO = prescriptionDTO;
        this.followUpDate = followUpDate;
        this.createdAt = createdAt;
    }

    public AppointmentRecordDTO() {
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
        this.tests = tests;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public PrescriptionDTO getPrescriptionDTO() {
        return prescriptionDTO;
    }

    public void setPrescriptionDTO(PrescriptionDTO prescriptionDTO) {
        this.prescriptionDTO = prescriptionDTO;
    }

    public LocalDateTime getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDateTime followUpDate) {
        this.followUpDate = followUpDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AppointmentRecord toEntity() {
        return new AppointmentRecord(this.id, this.patientId, this.doctorId, new Appointment(this.appointmentId), StringListConverter.convertListToString(this.symptoms), this.diagnosis, StringListConverter.convertListToString(this.tests), this.notes, this.referral, this.followUpDate, this.createdAt);
    }
}
