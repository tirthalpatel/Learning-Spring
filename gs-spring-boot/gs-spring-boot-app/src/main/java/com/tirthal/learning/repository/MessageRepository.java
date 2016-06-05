package com.tirthal.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tirthal.learning.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query // Added this annotation just to fix IDE validation error: Invalid derived query
	public Long countByTitle(String title);	
}
