package com.I_care.Appointment.Service.client;

import com.I_care.Appointment.Service.dto.CartItemDTO;
import com.I_care.Appointment.Service.dto.SaleItemDTO;
import com.I_care.Appointment.Service.dto.SaleRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "pharmacy-service", configuration = FeignClientInterceptor.class)
public interface PharmacyFeignClient {
    @PostMapping("/pharmacy/sales/create")
    Long createSales(@RequestBody SaleRequestDTO saleDTO);

    @PostMapping("/pharmacy/sales/saleItem/createMultiple")
    void createMultipleSaleItem(@RequestBody List<SaleItemDTO> items);
}
