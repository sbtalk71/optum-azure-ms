package com.demo.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dao.Inventory;
import com.demo.spring.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	@Autowired
	InventoryService inventoryService;

	//GET:http://localhost:8080/inventory/all
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Inventory>> getAll(){
		
		return ResponseEntity.ok(inventoryService.findAllProducts());
	}
	
	@GetMapping(path="/products/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Inventory> findProductById(@PathVariable("id") String productId){
		return ResponseEntity.ok(inventoryService.findInventoryByProductId(productId));
	}
	
	@GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Inventory> findOneById(@PathVariable("id") Long id){
		return ResponseEntity.ok(inventoryService.findInventoryId(id));
	}
	
	@PostMapping(path="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Inventory> saveInventory(@RequestBody Inventory inventory){
		return ResponseEntity.ok(inventoryService.saveToInventory(inventory));
	}
	
	
	
	
	
	
	
	
}
