package com.demo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dao.Inventory;
import com.demo.spring.dao.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	public List<Inventory> findAllProducts() {
		return inventoryRepository.findAll();
	}

	public Inventory findInventoryId(Long id) {

		return inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
	}

	public Inventory saveToInventory(Inventory inventory) {
		if (inventoryRepository.findByProductId(inventory.getProductId()).isEmpty()) {

			return inventoryRepository.save(inventory);

		} else {
			throw new RuntimeException("Inventory exists");
		}
	}

	public Inventory updateInventory(Inventory inventory) {
		if (inventoryRepository.existsById(inventory.getId())) {
			return inventoryRepository.save(inventory);

		} else {
			throw new RuntimeException("Inventory does not exist");
		}
	}

	public void deleteInventory(Long id) {
		if (inventoryRepository.existsById(id)) {
			inventoryRepository.deleteById(id);

		} else {
			throw new RuntimeException("Inventory does not exist");
		}
	}

	public Inventory findInventoryByProductId(String productId) {
		return inventoryRepository.findByProductId(productId).orElseThrow(() -> new RuntimeException("Not Found"));
	}
}
