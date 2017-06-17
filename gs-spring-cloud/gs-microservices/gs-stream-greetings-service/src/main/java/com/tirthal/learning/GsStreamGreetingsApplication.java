package com.tirthal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GsStreamGreetingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsStreamGreetingsApplication.class, args);
	}
}
