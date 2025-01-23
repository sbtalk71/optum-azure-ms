package com.example.eventsourcing.repository;

import com.example.eventsourcing.model.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProjectionRepository extends JpaRepository<OrderProjection, String> {
}
