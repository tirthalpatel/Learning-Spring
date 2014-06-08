package com.tirthal.learning.helloworld.xmlconfig;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TracingAspect {

	public void entering(JoinPoint joinPoint) {
		this.enteringCalled = true;
		log.trace("************** Entering to {}", joinPoint.getStaticPart().getSignature().toString());
	}

	public void exited(JoinPoint joinPoint) {
		this.exitedCalled = true;
		log.trace("************** Exited from {}", joinPoint.getStaticPart().getSignature().toString());
	}

	public boolean isEnteringCalled() {
		return enteringCalled;
	}

	public boolean isExitedCalled() {
		return exitedCalled;
	}

	boolean enteringCalled = false;
	boolean exitedCalled = false;

	Logger log = LoggerFactory.getLogger(TracingAspect.class);
}
