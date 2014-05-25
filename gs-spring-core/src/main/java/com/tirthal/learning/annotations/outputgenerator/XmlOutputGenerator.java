package com.tirthal.learning.annotations.outputgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("xmlOutput")
public class XmlOutputGenerator implements IOutputGenerator {

	private static final Logger log = LoggerFactory.getLogger(XmlOutputGenerator.class);
	
	public void generateOutput() {
		log.debug("XML Output Generator");
	}

}
