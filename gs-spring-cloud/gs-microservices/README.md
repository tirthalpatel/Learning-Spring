# Getting Started with Microservices using Spring Cloud

With the advent of microservice and cloud-native application architectures, building distributed systems is becoming increasingly common for the enterprise Java developer. 

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems. Many of these patterns are provided via wrapping the battle-tested components found at NetflixOSS.

This is maven based multi-module project structure. The parent project contains various child projects to get started with [Spring Cloud Features](http://cloud.spring.io/spring-cloud-static/spring-cloud.html). 

## Key Learning Goals

* __Distributed / Versioned Configuration__ --- Spring Cloud Config
* __Service Registration and Discovery__ --- Spring Cloud Netflix Eureka
* __Routing and Client Side Load Balancing__ --- Ribbon-Enabled RestTemplate with Eureka
* __Fault Tolerance with Circuit Breakers__ --- Spring Cloud Netflix Hystrix Client
* __Intelligent routing, filtering and server side load balancing__ --- Spring Cloud Netflix Zuul
* __API Gateway for Concurrent API Aggregation and Transformation__ --- RxJava with Spring MVC (included in Spring Cloud Netflix)
* __Monitoring via a near real-time operational insight platform__ --- Spring Cloud Netflix Hystrix Dashboard, Spectator, Servo, and Atlas
* __A Lightweight Message Broker for linking nodes of a Distributed System__ --- Spring Cloud Bus

## Prerequisite

- Java 1.8+
- Maven
- Spring STS IDE

## How to run projects?

All projects are Spring Boot and Maven based. Default port for each application is configured in respective properties file underneath `gs-config-repo` folder. To run an application with different port, pass argument: `-DPORT=12345`

* Using maven: `mvnw spring-boot:run` / `mvn spring-boot:run` 
* Using Boot Dashboard in STS
* Build the application and run it as jar file: `mvn clean install` followed by `java -jar target/<artifactId>-1.0.jar` 

## Run projects in following order

For more detail, refer 'README' of each project.

### Demo of Spring Cloud Config and Spring Cloud Netflix OSS (Eureka, Ribbon, Hystrix and Zuul)

1. `gs-config-server`: This is cloud config server, which internally refers configurations in `gs-config-repo`.
2. `gs-config-client` (Optional): This is demo client project leveraging cloud config server features.
3. `gs-discovery-eureka-server`: This is Eureka server for service discovery.
4. `gs-xxng-service`: This is demo Xing Xong microservice, registered with Eureka server.
5. `gs-xxng-client`: This is demo client, which consumes Xing Xong service using Ribbon with Eureka and enables circuit breaker pattern using Hystrix.
6. `gs-hystrix-dashboard`: This is a Hystrix Dashboard with Turbine to display the health of each circuit breaker in an efficient manner.
7. `gs-zuul-proxy-server`: This is a Zuul proxy server for routing, filtering and server side load balancing.

### Demo of API Gateway using RxJava

1. `gs-config-server`: This is cloud config server.
2. `gs-discovery-eureka-server`: This is Eureka server for service discovery.
3. `gs-games-catalog-service`: Just a plain-vanila games catalog microservice.
4. `gs-games-review-service`: Just a plain-vanila games reviews microservice.
5. `gs-games-recommendation-service`: Just a plain-vanila games recommendations microservice.
6. `gs-games-detail-rxjava-gateway`: This is demo of API Gateway using RxJava for concurrent API aggregation and transformation of `gs-games-catalog-service`, `gs-games-recommendation-service` and `gs-games-review-service`.

## TODO

* Enable to run via script or docker compose

## Also Refer

* [Spring Cloud Project](http://projects.spring.io/spring-cloud/)
* [Wiring Microservices with Spring Cloud](https://www.infoq.com/articles/spring-cloud-service-wiring)
* [Build self-healing distributed systems with Spring Cloud](http://www.javaworld.com/article/2927920/cloud-computing/build-self-healing-distributed-systems-with-spring-cloud.html)
* [Building Microservices with Spring Cloud and Docker](http://www.kennybastani.com/2015/07/spring-cloud-docker-microservices.html)

## Disclaimer

The code snippet are mainly targeted for learning purpose, and not focused for producing production code quality.