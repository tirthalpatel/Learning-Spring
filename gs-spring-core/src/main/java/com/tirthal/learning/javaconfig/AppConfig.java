package com.tirthal.learning.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public Foo foo() {
		return new Foo();
	}
	
	@Bean
	public Bar bar() {
		return new Bar(foo());
	}
	
}
