package com.tirthal.learning.helloworld.outputgenerator;

/**
 * Output Helper class
 * 
 * @author tirthalp
 * 
 */
public class OutputHelper {

	private IOutputGenerator generator;

	public OutputHelper() {
		generator = new XmlOutputGenerator();
	}

	public void setGenerator(IOutputGenerator generator) {
		this.generator = generator;
	}

	public void generateOutput() {
		generator.generateOutput();
	}
}
