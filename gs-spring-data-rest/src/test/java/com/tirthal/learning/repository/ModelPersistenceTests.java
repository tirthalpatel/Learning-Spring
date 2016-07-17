package com.tirthal.learning.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tirthal.learning.Main;
import com.tirthal.learning.model.Model;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class ModelPersistenceTests {

	@Autowired
	private ModelJpaRepository modelJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Model m = new Model();
		m.setFrets(10);
		m.setName("Test Model");
		m.setPrice(BigDecimal.valueOf(55L));
		m.setWoodType("Maple");
		m.setYearFirstMade(new Date());
		m = modelJpaRepository.save(m);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Model otherModel = modelJpaRepository.findOne(m.getId());
		assertEquals("Test Model", otherModel.getName());
		assertEquals(10, otherModel.getFrets());
		
		//delete BC location now
		modelJpaRepository.delete(otherModel);
		
		modelJpaRepository.aCustomMethod();
	}

	@Test
	public void testGetModelsInPriceRange() throws Exception {
		List<Model> mods = modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));
		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsByPriceRangeAndWoodType() throws Exception {
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		Pageable page = new PageRequest(0, 2, sort);
		Page<Model> mods = modelJpaRepository.queryByPriceRangeAndWoodType(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "Maple", page);
		assertEquals(2, mods.getSize());
	}

	@Test
	public void testGetModelsByType() throws Exception {
		List<Model> mods = modelJpaRepository.findAllModelsByType("Electric");
		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsByTypes() throws Exception {
		List<String> types = new ArrayList<String>();
		types.add("Electric");
		types.add("Acoustic");
		List<Model> mods = modelJpaRepository.findByModelTypeNameIn(types);
		
		mods.forEach((model) -> {
			assertTrue(model.getModelType().getName().equals("Electric") || 
					model.getModelType().getName().equals("Acoustic"));
		});
	}
}
