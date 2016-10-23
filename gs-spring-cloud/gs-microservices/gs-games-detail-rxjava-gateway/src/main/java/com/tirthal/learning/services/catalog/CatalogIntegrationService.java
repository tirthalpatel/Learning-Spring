package com.tirthal.learning.services.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;

import rx.Observable;
import rx.Subscriber;

@Service
public class CatalogIntegrationService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@HystrixCommand(fallbackMethod="getGameFallback", observableExecutionMode=ObservableExecutionMode.EAGER)
	public Observable<Game> getGame(final String glId) {
		return Observable.create(new Observable.OnSubscribe<Game>() {
			@Override
			public void call(Subscriber<? super Game> observer) {
				try {
					if(!observer.isUnsubscribed()) {
						observer.onNext(restTemplate.getForObject(env.getProperty("gs.games.catalog.service.rest.endpoint"), Game.class, glId));
						observer.onCompleted();
					}
				} catch(Exception e) {
					observer.onError(e);
				}
			}			
		});
	}
	
	@SuppressWarnings("unused")
	private Observable<Game> getGameFallback(final String glId) {
		Game g = new Game();
		g.setGlId(glId);
		g.setTitle("Oops, wrong title. Try again!");
		return Observable.just(g);
	}
}
