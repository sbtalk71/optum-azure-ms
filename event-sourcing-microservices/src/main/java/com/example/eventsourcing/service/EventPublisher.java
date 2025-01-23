package com.example.eventsourcing.service;

import com.example.eventsourcing.events.DomainEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public EventPublisher(KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(DomainEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
