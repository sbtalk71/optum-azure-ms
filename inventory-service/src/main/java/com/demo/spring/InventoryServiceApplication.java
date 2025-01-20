package com.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {
	
	Logger logger=LoggerFactory.getLogger(MyTestRunner.class);
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	 CommandLineRunner myrunner2() {
		
		return (args)->logger.info("2. My First Application is up...");
	}
}
