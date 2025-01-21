package com.demo.spring.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	Optional<Inventory> findByProductId(String productId);
}
