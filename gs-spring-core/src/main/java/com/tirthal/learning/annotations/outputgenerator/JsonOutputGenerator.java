package com.tirthal.learning.annotations.outputgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("jsonOutput")
public class JsonOutputGenerator implements IOutputGenerator {

	private static final Logger log = LoggerFactory.getLogger(JsonOutputGenerator.class);
	
	public void generateOutput() {
		log.debug("JSON Output Generator");
	}
	
}
