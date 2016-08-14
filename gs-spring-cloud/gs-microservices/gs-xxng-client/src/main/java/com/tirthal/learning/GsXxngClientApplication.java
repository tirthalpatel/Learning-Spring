package com.tirthal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class GsXxngClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsXxngClientApplication.class, args);
	}

    @Primary
    @Bean
    RestTemplate primaryRT() {
        return new RestTemplate();
    }
    
	// Automatically configured to use Ribbon
	@LoadBalanced
    @Bean
    RestTemplate loadBalancedRT() {
        return new RestTemplate();
    }
}