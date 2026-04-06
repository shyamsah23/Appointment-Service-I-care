package com.I_care.Appointment.Service.dto;

import java.time.LocalDateTime;

public class SaleRequestDTO {
    private Long prescriptionId;
    private Double totalAmount;
    private LocalDateTime saleDate;

    public SaleRequestDTO() {
    }

    public SaleRequestDTO(Long prescriptionId, Double totalAmount,LocalDateTime saleDate) {
        this.prescriptionId = prescriptionId;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
}
