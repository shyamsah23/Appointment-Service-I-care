package com.I_care.Appointment.Service.dto;

import com.I_care.Appointment.Service.enums.Status;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private Long id;
    private Long patientId;
    private long doctorId;
    private LocalDateTime appointmentDate;
    private Status status;
    private String reason;
    private String notes;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id, Long patientId, long doctorId, LocalDateTime appointmentDate, Status status, String reason, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.reason = reason;
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

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
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

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
