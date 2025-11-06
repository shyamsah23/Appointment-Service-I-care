package com.I_care.Appointment.Service.dto;

import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;

import java.time.LocalDateTime;

public class AppointmentDetails {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private LocalDateTime appointmentDate;
    private Status status;
    private String reason;
    private String notes;

    public AppointmentDetails() {
    }

    public AppointmentDetails(Long id, Long patientId, Long doctorId, String doctorName, String patientName, LocalDateTime appointmentDate, Status status, String reason, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
