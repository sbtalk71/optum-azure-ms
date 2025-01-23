package com.demo.spring.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent extends Event {
	private String name;

	public ProductCreatedEvent(String productId, String name) {
		super(productId);
		this.name = name;
	}
}
