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
	
	
	public List<Inventory> findAllProducts(){
		return inventoryRepository.findAll();
	}
}
