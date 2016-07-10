package com.tirthal.learning.repository.springdata;

public class ModelJpaRepositoryImpl implements ModelJpaRepositoryCustom{

	@Override
	public void helloWorldCustomMethod() {
		System.out.println("Hello World, How are you?");
	}
	
}
