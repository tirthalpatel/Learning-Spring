package com.tirthal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GsZuulProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsZuulProxyServerApplication.class, args);
	}
}