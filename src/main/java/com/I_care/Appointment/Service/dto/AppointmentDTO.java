package com.I_care.Appointment.Service.dto;

import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Status status;
    private String reason;
    private boolean paid;
    private String notes;
    private String paymentId;   // razorpayPaymentId
    private Double amount;      // consultation fee
    private Boolean refunded = false;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id, Long patientId, Long doctorId, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, Status status, String reason, boolean paid, String notes, String paymentId, Double amount, Boolean refunded) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.reason = reason;
        this.paid = paid;
        this.notes = notes;
        this.paymentId = paymentId;
        this.amount = amount;
        this.refunded = refunded;
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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

    public boolean isPaid() { return paid;}

    public void setPaid(boolean paid) { this.paid = paid;}

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public Appointment toEntity() {
        return new Appointment(this.id, this.patientId, this.doctorId, this.appointmentDate,this.startTime,this.endTime, this.status, this.reason,this.paid, this.notes,this.paymentId ,this.amount,this.refunded);
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", paid=" + paid +
                ", notes='" + notes + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", amount='" + amount + '\'' +
                ", refunded='" + refunded + '\'' +
                '}';
    }
}
