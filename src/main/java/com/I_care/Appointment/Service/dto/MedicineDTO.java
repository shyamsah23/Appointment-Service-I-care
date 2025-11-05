package com.I_care.Appointment.Service.dto;

import com.I_care.Appointment.Service.entity.Medicine;
import com.I_care.Appointment.Service.entity.Prescription;

public class MedicineDTO {
    private Long id;
    private String name;
    private Long medicineId;
    private String dosage;
    private String frequency;
    private Integer duration;
    private String type;
    private String route;
    private String instructions;
    private Long prescriptionId;

    public MedicineDTO(Long id, String name, Long medicineId, String dosage, String frequency, Integer duration, String type, String route, String instructions, Long prescriptionId) {
        this.id = id;
        this.name = name;
        this.medicineId = medicineId;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.type = type;
        this.route = route;
        this.instructions = instructions;
        this.prescriptionId = prescriptionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Medicine toEntity() {
        return new Medicine(this.id, this.name, this.medicineId, this.dosage, this.frequency, this.duration, this.type, this.route, this.instructions, new Prescription(this.prescriptionId));
    }
}
