package com.tirthal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GsBusByeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsBusByeServiceApplication.class, args);
	}
}