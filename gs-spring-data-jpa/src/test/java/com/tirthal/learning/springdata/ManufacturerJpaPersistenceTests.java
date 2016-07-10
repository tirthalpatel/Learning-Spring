package com.tirthal.learning.springdata;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tirthal.learning.model.Manufacturer;
import com.tirthal.learning.repository.springdata.ManufacturerJpaRepository;



@ContextConfiguration(locations={"classpath:com/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerJpaPersistenceTests {
	
	@Autowired
	private ManufacturerJpaRepository manufacturerJpaRepository;

	@Test
	public void testGetManufacturersFoundedBeforeDate() throws Exception {
		List<Manufacturer> mans = manufacturerJpaRepository.findByFoundedDateBefore(new Date());
		assertEquals(2, mans.size());
	}

	@Test
	public void testGetManufactureByName() throws Exception {
		Manufacturer m = manufacturerJpaRepository.findByNameLike("Fender%");
		assertEquals("Fender Musical Instruments Corporation", m.getName());
	}

	@Test
	public void testGetManufacturersThatSellModelsOfType() throws Exception {
		List<Manufacturer> mans = manufacturerJpaRepository.getAllThatSellAcoustics("Semi-Hollow Body Electric");
		assertEquals(1, mans.size());
	}
}
