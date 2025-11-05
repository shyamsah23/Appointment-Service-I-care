package com.I_care.Appointment.Service.entity;

import com.I_care.Appointment.Service.dto.MedicineDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Medicine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long medicineId;
    private String dosage;
    private String frequency;// 1-0-1
    private Integer duration;
    private String type; // tablet syrup
    private String route; // oral injection
    private String instructions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="prescription_id")
    private Prescription prescription;

    public Medicine(Long id, String name, Long medicineId, String dosage, String frequency, Integer duration, String type, String route, String instructions, Prescription prescription) {
        this.id = id;
        this.name = name;
        this.medicineId = medicineId;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.type = type;
        this.route = route;
        this.instructions = instructions;
        this.prescription = prescription;
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

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public MedicineDTO toDTO(){
        return new MedicineDTO(this.id,this.name,this.medicineId,this.dosage,this.frequency,this.duration, this.type,this.route,this.instructions, this.prescription.getId());
    }
}