package com.tirthal.learning.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tirthal.learning.model.ModelType;

public interface ModelTypeJpaRepository extends JpaRepository<ModelType, Long> {

}
