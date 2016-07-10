package com.tirthal.learning.model.projections;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.tirthal.learning.model.Model;
import com.tirthal.learning.model.ModelType;

@Projection(name="modelDetailView", types = {Model.class})
public interface ModelDetailView {
	// Here getter method names are not necessary match with attribute names of Model class, 
	// but it must match within @Value target.<model's attribute name>  
	@Value("#{target.name}")
	String getModelName();
		
	@Value("#{target.manufacturer.name}")
	String getManufacturerName(); // Just return manufacturer name instead of complete manufacturer payload
	
	@Value("#{target.manufacturer.name.split(' ')[0]} #{target.name}")
	String getFullName();	
	
	// Below getter method names must match with attribute names of Model class	
	BigDecimal getPrice();	
	ModelType getModelType();
	int getFrets();
	String getWoodType();
}
