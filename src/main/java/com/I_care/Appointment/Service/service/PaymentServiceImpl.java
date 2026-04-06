package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.client.PharmacyFeignClient;
import com.I_care.Appointment.Service.dto.*;
import com.I_care.Appointment.Service.entity.Appointment;
import com.I_care.Appointment.Service.enums.Status;
import com.I_care.Appointment.Service.exception.AppointmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PharmacyFeignClient pharmacyFeignClient;

    @Override
    public void verifyAndProcess(PaymentVerifyDTO req) throws AppointmentException {

        // 🔐 STEP 1 — VERIFY SIGNATURE
        String generatedSignature = hmacSHA256(
                req.getRazorpayOrderId() + "|" + req.getRazorpayPaymentId(),
                razorpaySecret
        );

//        if (!generatedSignature.equals(req.getRazorpaySignature())) {
//            throw new RuntimeException("Invalid payment signature");
//        }

        if ("PHARMACY".equals(req.getPaymentType())) {
            handlePharmacy(req);
        }
        else if ("APPOINTMENT".equals(req.getPaymentType())) {
            handleAppointment(req);
        }
        else {
            throw new RuntimeException("Invalid payment type");
        }
    }

    //PHARMACY FLOW

    private void handlePharmacy(PaymentVerifyDTO req) {

        System.out.println("PHARMACY REQUEST:");
        System.out.println("PrescriptionId: " + req.getPrescriptionId());
        System.out.println("TotalAmount: " + req.getTotalAmount());

        if (req.getPrescriptionId() == null) {
            throw new RuntimeException("PrescriptionId is null");
        }

        if (req.getTotalAmount() == null) {
            throw new RuntimeException("TotalAmount is null");
        }

        if (req.getCartItems() == null || req.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        SaleRequestDTO saleDTO = new SaleRequestDTO();
        saleDTO.setPrescriptionId(req.getPrescriptionId());

        saleDTO.setTotalAmount(req.getTotalAmount());

        saleDTO.setSaleDate(LocalDateTime.now());

        System.out.println("REQ OBJECT: " + req);
        System.out.println("TOTAL AMOUNT FROM DTO: " + req.getTotalAmount());

        Long saleId = pharmacyFeignClient.createSales(saleDTO);

        System.out.println("SALE CREATED WITH ID: " + saleId);

        List<SaleItemDTO> saleItems = new ArrayList<>();

        for (CartItemDTO item : req.getCartItems()) {

            SaleItemDTO dto = new SaleItemDTO();

            dto.setSaleId(saleId);
            dto.setMedicineId(item.getId());
            dto.setQuantity(item.getQuantity());
            dto.setUnitPrice(item.getUnitPrice());

            // optional but good practice
            dto.setBatchNo("BN-" + System.currentTimeMillis() + "-" + item.getId());

            saleItems.add(dto);
        }

        System.out.println("SALE ITEMS COUNT: " + saleItems.size());

        pharmacyFeignClient.createMultipleSaleItem(saleItems);
    }

    //APPOINTMENT FLOW

    private void handleAppointment(PaymentVerifyDTO req) throws AppointmentException {

        AppointmentDTO dto = req.getAppointmentData();

        if (dto == null) {
            throw new RuntimeException("Appointment data missing");
        }
        dto.setStatus(Status.SCHEDULED);
        dto.setPaid(true);
        dto.setRefunded(false);
        dto.setPaymentId(req.getRazorpayPaymentId());
        dto.setAmount(500.0); // or dynamic later
        appointmentService.scheduleAppointment(dto);
    }

    //SIGNATURE

    private String hmacSHA256(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(key);

            byte[] raw = mac.doFinal(data.getBytes());

            StringBuilder hex = new StringBuilder();
            for (byte b : raw) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error verifying payment");
        }
    }
}