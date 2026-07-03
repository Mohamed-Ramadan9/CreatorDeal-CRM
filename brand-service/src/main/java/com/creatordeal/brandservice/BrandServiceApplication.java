package com.creatordeal.brandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BrandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandServiceApplication.class, args);
	}

}
