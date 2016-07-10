package com.tirthal.learning.springdata;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tirthal.learning.model.Model;
import com.tirthal.learning.repository.springdata.ModelJpaRepository;



@ContextConfiguration(locations={"classpath:com/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelJpaPersistenceTests {
	
	@Autowired
	private ModelJpaRepository modelJpaRepository;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Model m = new Model();
		m.setFrets(10);
		m.setName("Test Model");
		m.setPrice(BigDecimal.valueOf(55L));
		m.setWoodType("Maple");
		m.setYearFirstMade(new Date());
		m = modelJpaRepository.saveAndFlush(m);

		Model otherModel = modelJpaRepository.findOne(m.getId());
		assertEquals("Test Model", otherModel.getName());
		assertEquals(10, otherModel.getFrets());
		
		//delete BC location now 
		modelJpaRepository.delete(otherModel);
	}

	@Test
	public void testGetModelsInPriceRange() throws Exception {
		List<Model> mods = modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));
		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsByPriceRangeAndWoodType() throws Exception {
		List<Model> mods = modelJpaRepository.queryByPriceRangeAndWoodType(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%");
		assertEquals(3, mods.size());
	}

	@Test
	public void testGetModelsByType() throws Exception {
		List<Model> mods = modelJpaRepository.findModelsByType("Electric");
		assertEquals(4, mods.size());
	}
	
	// ---- Trying few extra tests compare to standard repository tests in "ModelPersistenceTests.java"
	
	// Testing to see how to paging works
	@Test
	public void testGetModelsByPriceRangeAndWoodTypeWithPagination() throws Exception {
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		Pageable page = new PageRequest(0, 2, sort);
		Page<Model> mods = modelJpaRepository.queryByPriceRangeAndWoodTypeWithPagination(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%", page);
		assertEquals(2, mods.getSize());
	}
	
	// Testing method of custom repository
	@Test
	public void testCustomRepositoryMethod() {
		modelJpaRepository.helloWorldCustomMethod();
	}
}
