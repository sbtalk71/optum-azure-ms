package com.demo.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.spring.exceptions.OrderExistsException;

@RestControllerAdvice
public class OrdersExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleExceptions(RuntimeException ex){
		String msg="""
				{
				"message":%s
				}
				""";
		String resp=String.format(msg, ex.getMessage());
		return ResponseEntity.ok(resp);
	}
	
	
}
