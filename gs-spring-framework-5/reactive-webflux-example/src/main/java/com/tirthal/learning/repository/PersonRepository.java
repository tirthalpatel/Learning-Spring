package com.tirthal.learning.repository;

import com.tirthal.learning.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
