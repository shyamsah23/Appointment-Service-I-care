package com.I_care.Appointment.Service.config;

import com.I_care.Appointment.Service.utility.AppointmentConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaConsumerConfig {

    Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @KafkaListener(topics = AppointmentConstant.KAFKA_TEST_TOPIC, groupId = AppointmentConstant.GROUP_ID)
    public void consumer(String message) {
        logger.info("Consuming Message ={}", message);
    }
}
