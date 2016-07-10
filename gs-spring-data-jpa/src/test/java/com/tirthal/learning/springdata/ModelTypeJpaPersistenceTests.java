package com.tirthal.learning.springdata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tirthal.learning.model.ModelType;
import com.tirthal.learning.repository.springdata.ModelTypeJpaRepository;



@ContextConfiguration(locations={"classpath:com/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTypeJpaPersistenceTests {
	
	@Autowired
	private ModelTypeJpaRepository modelTypeJpaRepository;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		ModelType mt = new ModelType();
		mt.setName("Test Model Type");
		mt = modelTypeJpaRepository.saveAndFlush(mt);

		ModelType otherModelType = modelTypeJpaRepository.findOne(mt.getId());
		assertEquals("Test Model Type", otherModelType.getName());
		
		modelTypeJpaRepository.delete(otherModelType);
	}

	@Test
	public void testFind() throws Exception {
		ModelType mt = modelTypeJpaRepository.findOne(1L);
		assertEquals("Dreadnought Acoustic", mt.getName());
	}
}
