package com.I_care.Appointment.Service.dto;

import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.entity.Prescription;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private LocalDate prescriptionDate;
    private String notes;
    private List<MedicineDTO> medicines;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(Long id, Long patientId, Long doctorId, Long appointmentId, LocalDate prescriptionDate, String notes, List<MedicineDTO> medicines) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.prescriptionDate = prescriptionDate;
        this.notes = notes;
        this.medicines = medicines;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<MedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineDTO> medicines) {
        this.medicines = medicines;
    }

    public Prescription toEntity() {
        return new Prescription(this.id, this.patientId, this.doctorId, new Appointment(this.appointmentId), this.prescriptionDate, this.notes);
    }
}
