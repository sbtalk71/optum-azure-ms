package com.demo.spring.exceptions;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderNotFoundException(String message) {
		super(message);
	}
	
}
