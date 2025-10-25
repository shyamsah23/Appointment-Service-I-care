package com.I_care.Appointment.Service.service;

import com.I_care.Appointment.Service.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceHelper {

    public EmailDTO getNotificationDetails(Long id, String type, String to, String subject) {
        return new EmailDTO(id, to, subject, type);
    }
}
