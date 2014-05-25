package com.tirthal.learning.helloworld.outputgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation to generate JSON output
 * 
 * @author tirthalp
 * 
 */
public class JsonOutputGenerator implements IOutputGenerator {

	private static final Logger log = LoggerFactory.getLogger(JsonOutputGenerator.class);

	public void generateOutput() {
		log.debug("JSON Output Generator");
	}

}
