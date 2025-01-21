package com.demo.spring.exceptions;

public class OrderExistsException extends RuntimeException {

	public OrderExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderExistsException(String message) {
		super(message);
	}
	
}
