package com.tirthal.learning.springdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tirthal.learning.model.Location;
import com.tirthal.learning.repository.springdata.LocationJpaRepository;

@ContextConfiguration(locations={"classpath:com/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationJpaPersistenceTests {

	@Autowired
	private LocationJpaRepository locationJpaRepository;
	
	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Location location = new Location();
		location.setCountry("Canada");
		location.setState("British Columbia");
		location = locationJpaRepository.saveAndFlush(location);
				
		Location otherLocation = locationJpaRepository.findOne(location.getId());
		assertEquals("Canada", otherLocation.getCountry());
		assertEquals("British Columbia", otherLocation.getState());
		
		//delete BC location now
		locationJpaRepository.delete(otherLocation);
	}

	@Test
	public void testFindWithLike() throws Exception {
		List<Location> locs = locationJpaRepository.findByStateLike("New" + "%");
		assertEquals(4, locs.size());
	}

	@Test
	@Transactional  //note this is needed because we will get a lazy load exception unless we are in a tx
	public void testFindWithChildren() throws Exception {
		Location arizona = locationJpaRepository.findOne(3L);
		assertEquals("United States", arizona.getCountry());
		assertEquals("Arizona", arizona.getState());
		
		assertEquals(1, arizona.getManufacturers().size());
		
		assertEquals("Fender Musical Instruments Corporation", arizona.getManufacturers().get(0).getName());
	}
	
	// ---- Trying few extra tests compare to standard repository tests in "LocationPersistenceTests.java"
	
	@Test
	public void testFindAll() {
		List<Location> locations = locationJpaRepository.findAll();		
		assertNotNull(locations);
	}
	
	public void testJpaAnd() {
		List<Location> locations = locationJpaRepository.findByStateAndCountry("Utah", "United States");
		assertNotNull(locations);
		
		assertEquals("Utah", locations.get(0).getCountry());
		assertEquals("United States", locations.get(0).getCountry());		
	}
}
