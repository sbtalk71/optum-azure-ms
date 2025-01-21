package com.demo.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.demo.spring.dao.Order;
import com.demo.spring.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	RestClient restClient;

	@PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {

		// update product inventory
		String updateResponse = restClient.get()
				.uri("http://localhost:8080/inventory/" + order.getProductId() + "/" + order.getQuantity() + "")
				.accept(MediaType.TEXT_PLAIN).retrieve().body(String.class);

		// create order after update
		if (updateResponse.equals("UPDATED")) {
			return ResponseEntity.ok(orderService.saveOrder(order));
		} else {
			throw new RuntimeException("Order Failed..");
		}

	}
}
