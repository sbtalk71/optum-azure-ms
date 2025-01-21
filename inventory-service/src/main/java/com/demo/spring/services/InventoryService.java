package com.demo.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

		Optional<Inventory> invOp = inventoryRepository.findByProductId(inventory.getProductId());

		if (invOp.isPresent()) {

			Inventory existingInventory = invOp.get();
			existingInventory.setStock(inventory.getStock());
			return inventoryRepository.save(existingInventory);

		} else {
			throw new ProductNotFoundException("Not Found");
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

	public String updateProducts(String productId, int qty) {

		Optional<Inventory> invOp = inventoryRepository.findByProductId(productId);

		if (invOp.isPresent()) {

			Inventory existingInventory = invOp.get();
			existingInventory.setStock(existingInventory.getStock() - qty);
			inventoryRepository.save(existingInventory);
			return "UPDATED";

		} else {
			return "FAILED";
		}

	}
}
