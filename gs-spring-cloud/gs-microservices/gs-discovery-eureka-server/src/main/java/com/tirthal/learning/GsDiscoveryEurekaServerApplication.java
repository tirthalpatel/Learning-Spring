package com.tirthal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GsDiscoveryEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsDiscoveryEurekaServerApplication.class, args);
	}
}
