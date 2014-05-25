package com.tirthal.learning.misc.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * Demo code snippet of using Spring IO Resource (without using Spring DI).
 * 
 * At the core, following are the utilities that can be used to get a resource. The Resource object returned by the
 * following classes is a simple and handy object containing all the info of the resource
 * 
 * 1. Using a url like http, ftp, file etc. We can use URLResource. This utility wraps the java.net.URL
 * 
 * 2. Getting a file system resource using FileSystemResource. This wraps the java.io.File
 * 
 * 3. ClasspathResource is used to get a resource from the classpath.
 * 
 * @author tirthalp
 * 
 */
public class AppDemoBasic {

	private static final Logger log = LoggerFactory.getLogger(AppDemoBasic.class);
	
	public static void main(String[] args) {
		
		Resource resource = null;

		log.debug("////////////// Spring's UrlResource sample /////////////////");
		try {
			resource = new UrlResource("file:///D:/Tirthal-LABs/xLocal-Git-Repo/Learning-Spring/gs-spring-core/src/main/java/com/tirthal/learning/misc/io/sample1.txt");
			try {
				fileReadWrite(resource);
			} catch (IOException e) {				
				e.printStackTrace();
			}	
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}			

		log.debug("////////////// Spring's FileSystemResource sample ////////////");
		resource = new FileSystemResource("src/main/java/com/tirthal/learning/misc/io/sample1.txt");
		try {
			fileReadWrite(resource);
		} catch (IOException e) {			
			e.printStackTrace();
		}	

		log.debug("/////// Spring's ClassPathResource sample ////////////");
		resource = new ClassPathResource("com/tirthal/learning/misc/io/sample1.txt");
		try {
			fileReadWrite(resource);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		resource = null;
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
