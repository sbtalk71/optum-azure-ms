package com.demo.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Inventory>> getAll(){
		
		return ResponseEntity.ok(inventoryService.findAllProducts());
	}
}
