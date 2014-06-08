package com.tirthal.learning.helloworld.annotationdriven;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect {	

	@Before("execution(void sayHello())")
	public void entering(JoinPoint joinPoint) {
		this.enteringCalled = true;
		log.trace("************** Entering to {}", joinPoint.getStaticPart().getSignature().toString());
	}

	@After("execution(void sayHello())")
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
