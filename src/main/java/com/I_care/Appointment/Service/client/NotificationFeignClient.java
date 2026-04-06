package com.I_care.Appointment.Service.client;

import com.I_care.Appointment.Service.dto.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", configuration = FeignClientInterceptor.class)
public interface NotificationFeignClient {

    @PostMapping("api/mail/htmlMail")
    public void sendMail(@RequestBody EmailDTO emailDTO);

}
