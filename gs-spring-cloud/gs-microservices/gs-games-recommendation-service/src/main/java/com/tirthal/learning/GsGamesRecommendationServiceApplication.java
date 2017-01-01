package com.tirthal.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class GsGamesRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsGamesRecommendationServiceApplication.class, args);
	}
}

@RestController
class ExampleGameRecommendationsRestController {

	@RequestMapping(value = "/recommendations", method = RequestMethod.GET)
	public ResponseEntity<Iterable<List<Game>>> allRecommendations() {
		return new ResponseEntity<>(SampleData.RECOMMENDATIONS.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/games/{glId}/recommendations", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> recommendationsByGame(@PathVariable String glId) {
		
		if(SampleData.RECOMMENDATIONS.containsKey(glId))
			return new ResponseEntity<>(SampleData.RECOMMENDATIONS.get(glId), HttpStatus.OK);
		else
			return new ResponseEntity<>(SampleData.RECOMMENDATIONS.get("G000"), HttpStatus.NOT_FOUND);
	}

}

@Component
class SampleData implements CommandLineRunner {

	public static Map<String, List<Game>> RECOMMENDATIONS = new HashMap<>();
	
	@Override
	public void run(String... arg0) throws Exception {
		
		RECOMMENDATIONS.put("G000", createRecommendationsList(new Game(0L, "G000", "No Recommendation")));
		RECOMMENDATIONS.put("G100", createRecommendationsList(new Game(1L, "G801", "The Amazing Spider-Man"), new Game(2L, "G887", "Iron Man 3")));
		RECOMMENDATIONS.put("G200", createRecommendationsList(new Game(3L, "G963", "Boom Blox"), new Game(4L, "G749", "Sudoku")));
	}
	
	private List<Game> createRecommendationsList(Game... recommendation) {
		List<Game> recommendations = new ArrayList<>();
		for(Game  r : recommendation) {
			recommendations.add(r);
		}
		return recommendations;
	}
}
