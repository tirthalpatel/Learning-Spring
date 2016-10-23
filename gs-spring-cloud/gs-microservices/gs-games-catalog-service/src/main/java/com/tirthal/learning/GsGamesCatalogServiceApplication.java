package com.tirthal.learning;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class GsGamesCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsGamesCatalogServiceApplication.class, args);
	}

}

@RestController
class ExampleGamesRestController {

	@RequestMapping(value = "/catalog/games", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Game>> games() {
		return new ResponseEntity<>(SampleData.GAMES.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalog/games/{glId}", method = RequestMethod.GET)
	public ResponseEntity<Game> game(@PathVariable String glId) {
		
		if(SampleData.GAMES.containsKey(glId))
			return new ResponseEntity<>(SampleData.GAMES.get(glId), HttpStatus.OK);
		else
			return new ResponseEntity<>(SampleData.GAMES.get("G000"), HttpStatus.NOT_FOUND);
	}

}

@Component
class SampleData implements CommandLineRunner {

	public static Map<String, Game> GAMES = new HashMap<>();
	
    public void run(String... args) {
    	GAMES.put("G000", new Game(0L, "G000", "Game Not Found", 0));
    	GAMES.put("G100", new Game(1L, "G100", "Angry Bird", 120));
    	GAMES.put("G300", new Game(2L, "G300", "Ninja Sparton", 60));
    	GAMES.put("G200", new Game(3L, "G200", "Candy Crush", 100));
    }
   
}