package com.tirthal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class GsZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsZipkinServerApplication.class, args);
	}
}
