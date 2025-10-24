package com.I_care.Appointment.Service.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;

public class FeignClientInterceptor {

    @Value("${secret.header.key:''}")
    private String secretHeaderKey;

    public RequestInterceptor globalInterceptor() {
        return requestTemplate -> requestTemplate.header("X-Secret-key", secretHeaderKey);
    }
}
