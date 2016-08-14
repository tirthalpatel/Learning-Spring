package com.tirthal.learning;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class GsXingXongServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsXingXongServiceApplication.class, args);
	}
}

@RestController
class XingXongRestController {

	@RequestMapping(method=RequestMethod.GET, value="/{input}")
	public ResponseEntity<String> convertXingToXong(@PathVariable String input) {
												
		if(input!=null && input.length()==4 && input.endsWith("ing")) {
			Function<String, String> transform = e -> e.charAt(0) + "ong";
			return new ResponseEntity<String>(transform.apply(input), HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST); 				
	}
	
}