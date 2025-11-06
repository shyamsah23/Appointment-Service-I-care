package com.I_care.Appointment.Service.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientInterceptor {

    @Value("${secret.header.key}")
    private String secretHeaderKey;

    @Bean
    public RequestInterceptor globalInterceptor() {
        return requestTemplate -> requestTemplate.header("X-Secret-Key", secretHeaderKey);
    }
}
