package com.I_care.Appointment.Service.dto;

public class CartItemDTO {

    private Long id; // medicineId
    private Integer quantity;
    private Double unitPrice;

    public CartItemDTO() {
    }

    public CartItemDTO(Long id, Integer quantity, Double unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}