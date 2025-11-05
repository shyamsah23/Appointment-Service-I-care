package com.I_care.Appointment.Service.entity;

import com.I_care.Appointment.Service.dto.AppointmentRecordDTO;
import com.I_care.Appointment.Service.utility.StringListConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AppointmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private String symptoms;
    private String diagnosis;
    private String tests;
    private String notes;
    private String referral;
    private LocalDateTime followUpDate;
    private LocalDateTime createdAt;

    public AppointmentRecord(Long id, Long patientId, Long doctorId, Appointment appointment, String symptoms, String diagnosis, String tests, String referral, String notes, LocalDateTime followUpDate, LocalDateTime createdAt) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointment = appointment;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.tests = tests;
        this.referral = referral;
        this.notes = notes;
        this.followUpDate = followUpDate;
        this.createdAt = createdAt;
    }

    public AppointmentRecord() {
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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
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

    public AppointmentRecordDTO toDTO() {
        return new AppointmentRecordDTO(appointment.getId(), this.id, this.patientId, this.doctorId, StringListConverter.convertStringToList(this.symptoms), this.diagnosis, StringListConverter.convertStringToList(this.tests), this.notes, this.referral, null, this.followUpDate, this.createdAt);
    }
}
