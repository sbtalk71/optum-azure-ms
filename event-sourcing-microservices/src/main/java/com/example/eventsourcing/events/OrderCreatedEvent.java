package com.example.eventsourcing.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent implements DomainEvent {
    private String orderId;
    private String product;
    private int quantity;
    private Instant timestamp;

    @Override
    public String getEventType() {
        return "OrderCreated";
    }
    
    @Override
    public Instant getTimestamp() {
    	// TODO Auto-generated method stub
    	return Instant.now();
    }
}
