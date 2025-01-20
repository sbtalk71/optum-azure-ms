package com.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyTestRunner implements CommandLineRunner{

	Logger logger=LoggerFactory.getLogger(MyTestRunner.class);
	@Override
	public void run(String... args) throws Exception {
		
		logger.info("My First Application is up...");
	}

}
