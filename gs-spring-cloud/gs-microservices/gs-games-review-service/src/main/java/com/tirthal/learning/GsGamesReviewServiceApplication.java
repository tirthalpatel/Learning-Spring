package com.tirthal.learning;

import java.time.LocalDate;
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
public class GsGamesReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsGamesReviewServiceApplication.class, args);
	}
}

@RestController
class ExampleGameReviewsRestController {

	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public ResponseEntity<Iterable<List<Review>>> allReviews() {
		return new ResponseEntity<>(SampleData.REVIEWS.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/games/{glId}/reviews", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> reviewsByGame(@PathVariable String glId) {
		
		if(SampleData.REVIEWS.containsKey(glId))
			return new ResponseEntity<>(SampleData.REVIEWS.get(glId), HttpStatus.OK);
		else
			return new ResponseEntity<>(SampleData.REVIEWS.get("G000"), HttpStatus.NOT_FOUND);
	}

}

@Component
class SampleData implements CommandLineRunner {

	public static Map<String, List<Review>> REVIEWS = new HashMap<>();
	
	@Override
	public void run(String... arg0) throws Exception {
		
		REVIEWS.put("G000", createReviewsList(new Review(0L, "G000", "NA", "No Review", "No review found!", 0, LocalDate.now().toString())));
		REVIEWS.put("G100", createReviewsList(new Review(1L, "G100", "Tirthal", "Awesome", "This is just an awesome game, try your anger level!", 4, LocalDate.now().minusDays(5).toString()),
											  new Review(2L, "G100", "Ian", "Stupid game for adults", "This is just a stupid game for adult, however, child may enjoy it!", 2, LocalDate.now().minusDays(10).toString())));
		REVIEWS.put("G200", createReviewsList(new Review(3L, "G200", "Om", "My all time favorite", "Superb game. I love to play any time.", 5, LocalDate.now().minusDays(2).toString()),
				  							  new Review(4L, "G200", "Tirthal", "Not working on iPhone 4", "The latest version is not working on iPhone 4. Please suggest.", 4, LocalDate.now().minusDays(20).toString())));
	}
	
	private List<Review> createReviewsList(Review... review) {
		List<Review> reviews = new ArrayList<>();
		for(Review  r : review) {
			reviews.add(r);
		}
		return reviews;
	}
}