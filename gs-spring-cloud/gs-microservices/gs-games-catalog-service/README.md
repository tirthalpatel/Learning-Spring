# Games Catalog MicroService

Just a plain-vanila Games Catalog MicroService, which leverages Spring Cloud stack. This service is used in `gs-games-detail-rxjava-gateway` POC.

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server

## Key Notes

This service creates hard-coded games sample data using Spring Boot's CommandLineRunner and returns in REST services response. Ideally, it could have interaction with chosen data storage using Spring Data. 

### Try

1. Return all games: `http://localhost:6002/catalog/games`
2. Return game by logical game id: `http://localhost:6002/catalog/games/G100`
3. Return 'Game Not Found', if game doesn't exist: `http://localhost:6002/catalog/games/G001`