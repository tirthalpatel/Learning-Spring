package com.tirthal.learning.annotations.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("customerBean")
public class Customer {
	
	@Value("#{addressBean}")
	private Address address;

	@Value("#{addressBean.country}")
	private String country;
 
	@Value("#{addressBean.getFullAddress('David')}")
	private String fullAddress;	
 
	@Value("#{testData.map['MapA']}")
	private String testMapA;
	
	@Value("#{testData.list[1]}")
	private String testListItem1;
	
	@Override
	public String toString() {
		return "Customer [address=" + address + "\n, country=" + country
				+ "\n, fullAddress=" + fullAddress + "]" 
				+ "\n, Test Data = Value of key 'MapA' in map is '" + testMapA +"' & second element of list is '" + testListItem1 + "'";
	}
}
