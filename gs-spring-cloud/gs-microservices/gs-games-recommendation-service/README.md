# Games Recommendation MicroService

Just a plain-vanila Games Recommendation MicroService, which leverages Spring Cloud stack. This service is used in `gs-games-detail-rxjava-gateway` POC.

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server

## Key Notes

This service creates hard-coded games recommendations sample data using Spring Boot's CommandLineRunner and returns in REST services response. Ideally, it could have interaction with chosen data storage using Spring Data to return recommendations based on user likes or preferences. 

### Try

1. Return all recommendations: `http://localhost:6004/recommendations`
2. Return recommendations for given logical game id: `http://localhost:6004/games/G100/recommendations`
3. Return 'No Recommendation"', if recommendation doesn't exist for given logical game id: `http://localhost:6004/games/G001/recommendations`