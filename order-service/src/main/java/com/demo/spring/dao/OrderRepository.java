package com.demo.spring.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
 
	public Optional<Order> findByOrderId(String orderId);
}
