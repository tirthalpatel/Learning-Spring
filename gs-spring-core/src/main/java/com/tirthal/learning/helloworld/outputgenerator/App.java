package com.tirthal.learning.helloworld.outputgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sample application code to demo power of Spring DI
 * 
 * @author tirthalp
 * 
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String arg[]) {

		log.debug("Option 1 - Call directly:");
		/*
		 * In this way, the problem is the “output” is coupled tightly to XmlOutputGenerator, every change of output
		 * generator may involve code change. If this code is scattered all over of your project, every change of the
		 * output generator will make you suffer seriously.
		 */
		IOutputGenerator output = new XmlOutputGenerator();
		output.generateOutput();
		log.debug("-------------------------------------");

		log.debug("Option 2 - Call it with helper class:");
		/*
		 * This looks more elegant, and you only need to manage a single helper class, however the helper class is still
		 * tightly coupled to XmlOutputGenerator, every change of output generator still involves minor code change.
		 */
		OutputHelper helper = new OutputHelper();
		helper.generateOutput();
		log.debug("-------------------------------------");

		log.debug("Option 3 - Using Spring dependency injection:");
		/*
		 * Now, you just need to change the Spring XML file for a different output generator. When output changed, you
		 * need to modify the Spring XML file only, no code changed, means less error. For example, you can easily
		 * switch between xml and json output generators or even assign newly implemented CSV generator without changing
		 * below client code.
		 * 
		 * This is cool... right!
		 */
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/helloworld/outputgenerator/app-container.xml");
		OutputHelper outputHelper = (OutputHelper) ctx.getBean("outputHelper");
		outputHelper.generateOutput();
	}

}
