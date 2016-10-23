# API Gateway using RxJava

This is demo of API Gateway using RxJava for concurrent API aggregation and transformation of `gs-games-catalog-service`, `gs-games-review-service` and `gs-games-recommendation-service`. Similar implementation approach could be used for "Backend For Frontends (BFF)" pattern too.

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server
	- [gs-games-catalog-service](../gs-games-catalog-service/README.md): Just a plain-vanila games catalog microservice
	- [gs-games-review-service](../gs-games-review-service/README.md): Just a plain-vanila games reviews microservice
	- [gs-games-recommendation-service](../gs-games-recommendation-service/README.md): Just a plain-vanila games recommendations microservice

## Key Notes

See 'GameDetailRestController.java' and follow code.

### Try

1. Return game detail by logical game id: `http://localhost:6001/game/G100`
2. What if game doesn't exist: `http://localhost:6001/game/G001`

## Reference

* [Reactive fault tolerant programming with Hystrix and RxJava](http://www.slideshare.net/mstine/reactive-fault-tolerant-programming-with-hystrix-and-rxjava) - [code](https://github.com/mstine/microservices-lab/tree/master/springbox-cloud/gateway)