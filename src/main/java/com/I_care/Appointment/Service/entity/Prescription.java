package com.I_care.Appointment.Service.entity;

import com.I_care.Appointment.Service.dto.PrescriptionDTO;
import com.I_care.Appointment.Service.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private LocalDate prescriptionDate;
    private String notes;

    public Prescription(Long id) {
        this.id = id;
    }

    public Prescription(Long id, Long patientId, Long doctorId, Appointment appointment, LocalDate prescriptionDate, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointment = appointment;
        this.prescriptionDate = prescriptionDate;
        this.notes = notes;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PrescriptionDTO toDTO() {
        return new PrescriptionDTO(this.id, this.patientId, this.doctorId, this.appointment.getId(), this.prescriptionDate, this.notes, null);
    }
}
