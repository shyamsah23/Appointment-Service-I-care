package com.I_care.Appointment.Service.config;

import com.I_care.Appointment.Service.utility.AppointmentConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true", matchIfMissing = false)
    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppointmentConstant.KAFKA_TEST_TOPIC)
                .partitions(2)
                .build();
    }
}
