package com.developer.marketspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketSpringApplication.class, args);
	}

}
