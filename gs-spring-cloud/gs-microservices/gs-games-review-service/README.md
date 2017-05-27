# Games Review MicroService

Just a plain-vanilla Games Review MicroService, which leverages Spring Cloud stack. This service is used in `gs-games-detail-rxjava-gateway` POC.

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server

## Key Notes

This service creates hard-coded games reviews sample data using Spring Boot's CommandLineRunner and returns in REST services response. Ideally, it could have interaction with chosen data storage using Spring Data. 

### Try

1. Return all reviews: `http://localhost:6003/reviews`
2. Return reviews for given logical game id: `http://localhost:6003/games/G100/reviews`
3. Return 'No Review"', if review doesn't exist for given logical game id: `http://localhost:6003/games/G001/reviews`