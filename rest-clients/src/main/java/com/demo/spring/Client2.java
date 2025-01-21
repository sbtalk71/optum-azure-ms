package com.demo.spring;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

public class Client2 {

	public static void main(String[] args) {
				
		Inventory inv=new Inventory("p005", "macbook Air", 4, 90000);
		
		RestClient client=RestClient.create();
		Inventory resp3=client
				.post()
				.uri("http://localhost:8080/inventory/save")
				.body(inv)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(Inventory.class);
		
		System.out.println(resp3);
	}

}
