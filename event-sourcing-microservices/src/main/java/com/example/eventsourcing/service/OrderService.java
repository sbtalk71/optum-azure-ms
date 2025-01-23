package com.example.eventsourcing.service;

import com.example.eventsourcing.events.OrderCreatedEvent;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderService {
    private final EventPublisher eventPublisher;

    public OrderService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void createOrder(String orderId, String product, int quantity) {
        OrderCreatedEvent event = new OrderCreatedEvent(orderId, product, quantity, Instant.now());
        eventPublisher.publish(event);
    }
}
