package com.tirthal.learning.helloworld.outputgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation to generate XML output
 * 
 * @author tirthalp
 * 
 */
public class XmlOutputGenerator implements IOutputGenerator {

	private static final Logger log = LoggerFactory.getLogger(XmlOutputGenerator.class);

	public void generateOutput() {
		log.debug("XML Output Generator");
	}

}
