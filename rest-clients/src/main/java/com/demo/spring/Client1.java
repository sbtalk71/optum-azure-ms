package com.demo.spring;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

public class Client1 {

	public static void main(String[] args) {
		RestTemplate restTemplate=new RestTemplate();
		
		String resp=restTemplate.getForObject("http://localhost:8080/inventory/4", String.class);
		
		
		System.out.println(resp);
		
		
		RestClient client=RestClient.create();
		String resp2=client
				.get()
				.uri("http://localhost:8080/inventory/4")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(String.class);
		
		System.out.println(resp2);
		
		
		Inventory resp3=client
				.get()
				.uri("http://localhost:8080/inventory/4")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(Inventory.class);
		
		System.out.println(resp3);
	}

}
