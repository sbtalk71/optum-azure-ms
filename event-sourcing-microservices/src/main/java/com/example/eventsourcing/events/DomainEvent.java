package com.example.eventsourcing.events;

import java.time.Instant;

public interface DomainEvent {
    String getEventType();
    Instant getTimestamp();
}
