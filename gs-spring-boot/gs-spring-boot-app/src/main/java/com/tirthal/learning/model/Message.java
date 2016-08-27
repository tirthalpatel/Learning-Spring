package com.tirthal.learning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="MESSAGES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@Size(min=1, max=255)	
	private String title;
		
	@Size(max=1000)	
	private String description;
	
	// Thanks to Lombok - no boiler plate code of constructors, getters, setters, toString, hashCode
}
