package com.tirthal.learning.model.projections;

import java.math.BigDecimal;

import org.springframework.data.rest.core.config.Projection;

import com.tirthal.learning.model.Manufacturer;
import com.tirthal.learning.model.Model;
import com.tirthal.learning.model.ModelType;

@Projection(name="modelDetail", types = {Model.class})
public interface ModelDetail {
	// Here getter method names must match with attribute names of Model class
	String getName();
	BigDecimal getPrice();
	Manufacturer getManufacturer();
	ModelType getModelType();
	int getFrets();
	String getWoodType();
}
