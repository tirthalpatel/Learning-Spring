package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stage {

	private static final Logger log = LoggerFactory.getLogger(Stage.class);

	private Stage() {

	}

	private static class StageSingletonHolder {
		static Stage instance = new Stage();
	}

	public static Stage getInstance() {
		return StageSingletonHolder.instance;
	}

	public void prepareStage() {
		log.debug("Stage preparation is in progress");
	}

	public void readyToPerform() {
		log.debug("Stage is ready to start performance");
	}

	public void closedToPerform() {
		log.debug("Stage is closed now");
	}
}
