package com.raket.customerorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CustomerOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerOrdersApplication.class, args);
	}

}
