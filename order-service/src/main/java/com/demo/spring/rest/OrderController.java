package com.demo.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.dao.Order;
import com.demo.spring.services.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	RestClient.Builder builder;
	
	@Autowired
	RestTemplate restTemplate;
	

	@PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {

		// update product inventory
		String updateResponse = builder.build().get()
				.uri("http://inventory-service/inventory/" + order.getProductId() + "/" + order.getQuantity() + "")
				.accept(MediaType.TEXT_PLAIN).retrieve().body(String.class);

		// create order after update
		if (updateResponse.equals("UPDATED")) {
			return ResponseEntity.ok(orderService.saveOrder(order));
		} else {
			throw new RuntimeException("Order Failed..");
		}

	}
	@GetMapping(path="/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(name="orderBackend",fallbackMethod ="getCatalogueFallback" )
	public ResponseEntity getCatalogue() {
		//return ResponseEntity.ok(restTemplate.getForObject("http://inventory-service/inventory", String.class));
		return ResponseEntity.ok(builder.build()
				.get().uri("http://inventory-service/inventory")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(String.class));
	}
	
	public ResponseEntity<String> getCatalogueFallback(Throwable t){
		String msg="""
				{
				"message":%s
				}
				""";
		String resp=String.format(msg, "Service Unavailable ");
		return ResponseEntity.ok(resp);
	}
}
