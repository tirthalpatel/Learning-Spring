package com.tirthal.learning.annotations.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("addressBean")
public class Address {

	@Value("A/108, Pushkar Flats")
	private String street;

	@Value("380054")
	private String postcode;

	@Value("#{'India'.toUpperCase()}")
	private String country;

	@Override
	public String toString() {
		return "Address [street=" + street + ", postcode=" + postcode
				+ ", country=" + country + "]";
	}

	public String getFullAddress(String prefix)
	{
		return prefix + " : " + street + " " + postcode + " " + country;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
