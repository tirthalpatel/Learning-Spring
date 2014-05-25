package com.tirthal.learning.misc.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * Demo code snippet of using Spring IO Resource improved using applicationContext.getResource() and Spring DI, compare to AppDemoBasic.
 * 
 * @author tirthalp
 *
 */
public class AppDemoImproved {
	
	private static final Logger log = LoggerFactory.getLogger(AppDemoImproved.class);
	
	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/tirthal/learning/misc/io/resourceContext.xml");
		
		log.debug("-------------");

		log.debug(" --- OPTION 1 - Direct use of applicationContext.getResource()");
		/*
		 * A point to note here is that since the ApplicationContext also implements theResourceLoader interface so it
		 * can also be used to get a resource. The default implementation for application context depends on the type of
		 * application context, in this case ClasspathResource
		 */
		Resource classPathResource = applicationContext.getResource("com/tirthal/learning/misc/io/sample2.txt");
		fileReadWrite(classPathResource);
		
		log.debug(" --- OPTION 2 - Use of Spring DI (Dependency Injection)");	
		ResourceInjection resourceInjection = applicationContext.getBean("resourceInjection", ResourceInjection.class);
		Resource springBeanInjectedResource = resourceInjection.getTextFile();
		fileReadWrite(springBeanInjectedResource);
		
		/*
		 * This returns a resource on run time based on certain condition or logic. This is accomplished by
		 * injecting a resource loader in the bean and then using it to get the resource. See method body
		 */
		Resource dynamicResourceViaResourceLoader = resourceInjection.loadDynamicResource();
		fileReadWrite(dynamicResourceViaResourceLoader);
	}
	
	private static void fileReadWrite(Resource rs) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = rs.getInputStream();
			Scanner scanner = new Scanner(inputStream);
			while (scanner.hasNext()) {
				log.debug(scanner.nextLine());
			}
			scanner.close();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
