package com.tirthal.learning.repository.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tirthal.learning.model.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {
	List<Location> findByStateLike(String stateName);
	
	// Few methods to try Query DSL
	List<Location> findFirstByStateLike(String stateName);
	List<Location> findTop5ByStateLike(String stateName);
	List<Location> findByStateOrCountry(String s, String c);
	List<Location> findByStateAndCountry(String s, String c);
	List<Location> findByStateNot(String s);
}
