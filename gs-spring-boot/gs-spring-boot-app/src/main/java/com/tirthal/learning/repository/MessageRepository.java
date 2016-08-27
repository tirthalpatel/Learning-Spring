package com.tirthal.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tirthal.learning.model.Message;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	public Long countByTitle(String title);	
}
