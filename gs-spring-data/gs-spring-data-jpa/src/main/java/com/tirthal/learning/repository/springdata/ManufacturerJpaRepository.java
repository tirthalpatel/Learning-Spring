package com.tirthal.learning.repository.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tirthal.learning.model.Manufacturer;

public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {
	List<Manufacturer> findByFoundedDateBefore(Date date);
	
	Manufacturer findByNameLike(String name);
	
	@Query(name="Manufacturer.getAllThatSellAcoustics", nativeQuery=true)
	List<Manufacturer> getAllThatSellAcoustics(String name);
}
