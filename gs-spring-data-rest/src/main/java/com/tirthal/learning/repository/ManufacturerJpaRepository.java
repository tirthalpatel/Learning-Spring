package com.tirthal.learning.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.tirthal.learning.model.Manufacturer;

@Repository
@RepositoryRestResource(path="mfgs", collectionResourceRel="mfgs") // Customizing service
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {
	List<Manufacturer> findByFoundedDateBefore(Date date);
	
	List<Manufacturer> findByActiveTrue();
	List<Manufacturer> findByActiveFalse();
	
	@Query(name="Manufacturer.getAllThatSellAcoustics", nativeQuery=true)
	List<Manufacturer> getAllThatSellAcoustics(String name);
}
