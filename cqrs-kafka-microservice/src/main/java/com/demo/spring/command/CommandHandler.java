package com.demo.spring.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.spring.kafka.Event;
import com.demo.spring.kafka.ProductCreatedEvent;
import com.demo.spring.kafka.ProductStockUpdatedEvent;

@Service
public class CommandHandler {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void handleCreateProduct(String productId, String name) {
        ProductCreatedEvent event = new ProductCreatedEvent(productId, name);
        kafkaTemplate.send("product-events", event);
    }

    public void handleUpdateStock(String productId, int quantity) {
        ProductStockUpdatedEvent event = new ProductStockUpdatedEvent(productId, quantity);
        kafkaTemplate.send("product-events", event);
    }
}
