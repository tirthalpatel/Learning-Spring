package com.tirthal.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger _log = LoggerFactory.getLogger(GameDetailRestController.class);
	
	@Autowired
	CatalogIntegrationService catalogIntegrationService;
	
	@Autowired
	ReviewIntegrationService reviewIntegrationService;
	
	@Autowired
	RecommendationIntegrationService recommendationIntegrationService;
	
	@RequestMapping(value = "/game/{glId}", method = RequestMethod.GET)
	public DeferredResult<GameDetail> gameDetail(@PathVariable String glId) {					
		
		_log.info("*** Before Observable.zip() operation");
		
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
					
					_log.info("*** Inside Observable.zip() operation");
					
					return detail;
				}
		);
		
		_log.info("*** After Observable.zip() operation");
		
		return toDeferredResult(gameDetail);
	}

	private DeferredResult<GameDetail> toDeferredResult(Observable<GameDetail> gameDetail) {		
		DeferredResult<GameDetail> result = new DeferredResult<>();

		_log.info("*** Before oberavable.subscribe()");
		
		gameDetail.subscribe(new Observer<GameDetail>() {

			@Override
			public void onCompleted() {				
				_log.info("*** Inside Observer's onCompleted()");
			}

			@Override
			public void onError(Throwable throwable) {	
				_log.info("*** Inside Observer's onError()");
				
				result.setErrorResult(throwable);
			}

			@Override
			public void onNext(GameDetail gameDetail) {	
				_log.info("*** Inside Observer's onNext()");
				
				result.setResult(gameDetail);
			}			
		});
		
		_log.info("*** After oberavable.subscribe()");
		
		return result;
	}
}
