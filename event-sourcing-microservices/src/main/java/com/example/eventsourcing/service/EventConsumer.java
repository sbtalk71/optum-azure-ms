package com.example.eventsourcing.service;

import com.example.eventsourcing.events.DomainEvent;
import com.example.eventsourcing.events.OrderCreatedEvent;
import com.example.eventsourcing.model.OrderProjection;
import com.example.eventsourcing.repository.OrderProjectionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    private final OrderProjectionRepository repository;

    public EventConsumer(OrderProjectionRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "order-consumers")
    public void consume(DomainEvent event) {
        if (event instanceof OrderCreatedEvent orderEvent) {
            OrderProjection projection = new OrderProjection(
                orderEvent.getOrderId(),
                orderEvent.getProduct(),
                orderEvent.getQuantity()
            );
            repository.save(projection);
        }
    }
}
