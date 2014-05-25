package com.tirthal.learning.annotations.outputgenerator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("output")
public class OutputHelper {
	
	@Autowired
	@Qualifier("jsonOutput")
	private IOutputGenerator generator;

	private static final Logger log = LoggerFactory.getLogger(OutputHelper.class);
	
	public OutputHelper() {
		generator = new XmlOutputGenerator();
	}
	
	public void setGenerator(IOutputGenerator generator) {
		this.generator = generator;
	}
	
	public void generateOutput()
	{
		generator.generateOutput();
	}
	
	@PostConstruct
	public void init()
	{
		log.debug("Pre-processing OutputHelper after bean construction...");
	}
	
	@PreDestroy
	public void clear()
	{
		log.debug("Cleanup processing OutputHelper before destroy...");
	}
}
