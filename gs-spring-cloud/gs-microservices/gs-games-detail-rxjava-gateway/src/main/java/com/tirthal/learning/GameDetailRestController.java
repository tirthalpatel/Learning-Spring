package com.tirthal.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirthal.learning.models.GameDetail;
import com.tirthal.learning.services.catalog.CatalogIntegrationService;
import com.tirthal.learning.services.recommendations.RecommendationIntegrationService;
import com.tirthal.learning.services.review.ReviewIntegrationService;

import rx.Observable;
import rx.Observer;

@RestController
public class GameDetailRestController {

	@Autowired
	CatalogIntegrationService catalogIntegrationService;
	
	@Autowired
	ReviewIntegrationService reviewIntegrationService;
	
	@Autowired
	RecommendationIntegrationService recommendationIntegrationService;
	
	@RequestMapping(value = "/game/{glId}", method = RequestMethod.GET)
	public DeferredResult<GameDetail> gameDetail(@PathVariable String glId) {					
		
		Observable<GameDetail> gameDetail = Observable.zip(
				catalogIntegrationService.getGame(glId),
				reviewIntegrationService.getReviews(glId),
				recommendationIntegrationService.getRecommendations(glId),
				(game, reviews, recommendations) -> {
					GameDetail detail = new GameDetail();
					detail.setGlId(game.getGlId());
					detail.setTitle(game.getTitle());
					detail.setReviews(reviews);
					detail.setRecommendations(recommendations);
					
					return detail;
				}
		);
		
		return toDeferredResult(gameDetail);
	}

	private DeferredResult<GameDetail> toDeferredResult(Observable<GameDetail> gameDetail) {
		DeferredResult<GameDetail> result = new DeferredResult<>();
		gameDetail.subscribe(new Observer<GameDetail>() {

			@Override
			public void onCompleted() {				
				
			}

			@Override
			public void onError(Throwable throwable) {				
				result.setErrorResult(throwable);
			}

			@Override
			public void onNext(GameDetail gameDetail) {				
				result.setResult(gameDetail);
			}			
		});
		
		return result;
	}
}
