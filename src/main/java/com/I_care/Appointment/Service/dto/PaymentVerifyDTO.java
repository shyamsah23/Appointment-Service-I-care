package com.I_care.Appointment.Service.dto;

import java.util.List;

public class PaymentVerifyDTO {

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private String paymentType; // PHARMACY / APPOINTMENT
    private Long prescriptionId;
    private Double totalAmount;
    private List<CartItemDTO> cartItems;
    private AppointmentDTO appointmentData;

    public PaymentVerifyDTO() {
    }

    public PaymentVerifyDTO(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature, String paymentType, Long prescriptionId, Double totalAmount, List<CartItemDTO> cartItems, AppointmentDTO appointmentData) {
        this.razorpayOrderId = razorpayOrderId;
        this.razorpayPaymentId = razorpayPaymentId;
        this.razorpaySignature = razorpaySignature;
        this.paymentType = paymentType;
        this.prescriptionId = prescriptionId;
        this.totalAmount = totalAmount;
        this.cartItems = cartItems;
        this.appointmentData = appointmentData;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public AppointmentDTO getAppointmentData() {
        return appointmentData;
    }

    public void setAppointmentData(AppointmentDTO appointmentData) {
        this.appointmentData = appointmentData;
    }
}
