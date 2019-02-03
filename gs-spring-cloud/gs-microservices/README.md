# Getting Started with Microservices using Spring Cloud

With the advent of microservice and cloud-native application architectures, building distributed systems is becoming increasingly common for the enterprise Java developer. 

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems. Many of these patterns are provided via wrapping the battle-tested components found at NetflixOSS.

This is maven based multi-module project structure. The parent project contains various child projects to get started with [Spring Cloud Version: Greenwich.RELEASE](http://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/).

Here, the focus is to experiment various microservices patterns quickly using Spring cloud technology stack with plain-vanilla approach instead of building microservices for complex real-world functional use cases.

## Key Learning Goals

Spring Cloud Upgrade from Dalston.SR1 to Greenwich.RELEASE @ [see](https://github.com/tirthalpatel/Learning-Spring/blob/master/gs-spring-cloud/gs-microservices/upgrade-notes.md)

* __Centralized External Configuration backed by a Git Repository__ --- Spring Cloud Config
* __Service Registry and Client Side Load Balanced Discovery__ --- Spring Cloud Netflix Eureka + Ribbon-Enabled RestTemplate with Eureka
* __Fault Tolerance with Circuit Breakers__ --- Spring Cloud Netflix Hystrix Client and Hystrix Dashboard
* __Intelligent routing, filtering and server side load balancing__ --- Spring Cloud Netflix Zuul
* __API Gateway for Concurrent API Aggregation and Transformation__ --- RxJava with Spring MVC (included in Spring Cloud Netflix)
* __Distributed Tracing__ --- Spring Cloud Sleuth and Twitter Zipkin (Sleuth is also compatible with HTrace and log-based tracing like ELK)
* __An Event Bus for linking services and service instances together using a Lightweight Message Broker__ --- Spring Cloud Bus (with RabbitMQ or Kafka)
* __Message Bus Abstraction Framework for building Event-driven Microservices__ --- Spring Cloud Stream (using RabbitMQ or Kafka Binder)
* __Secure Applications and Services with simplified OAuth 2.0 Flows__ --- Spring Cloud Security
* __Stubs for Service Contracts__ --- Spring Cloud Contracts
* __Monitoring via a near real-time operational insight platform__ --- Spring Cloud Netflix Spectator, Servo, and Atlas

## Prerequisite

- Java 1.8+
- Maven
- Spring STS 3.9.6.RELEASE+ (or choice of IDE)

## How to run demo?

Firstly, ensure to configure `spring.cloud.config.server.git.uri` correctly in `gs-config-server/src/main/resources/application.properties`

Then, you can choose any of following options to run demo:

### Option 1 - Run on local or on-premise machine

All projects are Spring Boot and Maven based. Default port for each application is configured in respective properties file underneath `gs-config-repo` folder. To run an application with different port, pass argument: `-DPORT=12345`

* Using maven: `mvnw spring-boot:run` / `mvn spring-boot:run` 
* Using Boot Dashboard in STS
* Build the application and run it as jar file: `mvn clean install` followed by `java -jar target/<artifactId>-1.0.jar` 

### Option 2 - Run on Pivotal Cloud Foundry (Cloud PaaS)

* Install [Cloud Foundry CLI](https://github.com/cloudfoundry/cli#downloads) 
* Create [Pivotal account](https://account.run.pivotal.io/sign-up) and organization (e.g. gs-microservices)
* Create domain: `cf create-domain <org-name> <domain-name-of-your-choice>.io` (e.g. cf create-domain gs-microservices tirthalpatel.io)
* To be added...

## Microservices Demo Projects

Run projects in following order. For more detail, refer 'README' of each project.

### Demo 1 - Spring Cloud Config + Spring Cloud Netflix OSS (Eureka, Ribbon, Hystrix and Zuul)

__Patterns__ : [Externalized Configuration](http://microservices.io/patterns/externalized-configuration.html), [Service Registry](http://microservices.io/patterns/service-registry.html), [Self Service Registration](http://microservices.io/patterns/self-registration.html), [Client-side Service Discovery](http://microservices.io/patterns/client-side-discovery.html), [Circuit Breaker](http://microservices.io/patterns/reliability/circuit-breaker.html)  

1. `gs-config-server`: This is cloud config server, which internally refers configurations in `gs-config-repo`.
2. `gs-config-client` (Optional): This is demo client project leveraging cloud config server features.
3. `gs-discovery-eureka-server`: This is Eureka server for service registry.
4. `gs-xxng-service`: This is demo Xing Xong microservice, registered with Eureka server.
5. `gs-xxng-client`: This is demo client, which consumes Xing Xong service using Ribbon with Eureka and enables circuit breaker pattern using Hystrix.
6. `gs-hystrix-dashboard`: This is a Hystrix Dashboard with Turbine to display the health of each circuit breaker in an efficient manner.
7. `gs-zuul-proxy-server`: This is a Zuul proxy server for routing, filtering and server side load balancing.

### Demo 2 - API Gateway using RxJava + Distributed Tracing using Spring Sleuth and Zipkin

__Patterns__ : [API Gateway](http://microservices.io/patterns/apigateway.html), [Distributed Tracking](http://microservices.io/patterns/observability/distributed-tracing.html)

1. `gs-config-server`: This is cloud config server.
2. `gs-discovery-eureka-server`: This is Eureka server for service registry.
3. `gs-games-catalog-service`: Just a plain-vanilla games catalog microservice. Also, enabled for automatic instrumentation of traces.
4. `gs-games-review-service`: Just a plain-vanilla games reviews microservice. Also, enabled for automatic instrumentation of traces.
5. `gs-games-recommendation-service`: Just a plain-vanilla games recommendations microservice. Also, enabled for automatic instrumentation of traces.
6. `gs-games-detail-rxjava-gateway`: This is demo of API Gateway using RxJava for concurrent API aggregation and transformation of `gs-games-catalog-service`, `gs-games-recommendation-service` and `gs-games-review-service`. Also, enabled for automatic instrumentation of traces.
7. `gs-zipkin-server`: This is zipkin server for visualizing and querying traces, which are received over HTTP from `gs-games-*` microservices.

### Demo 3 - Spring Cloud Bus + Spring Cloud Stream

__Dependency__ : RabbitMQ (Alternatively Kafka is also supported by Spring Cloud Bus and Spring Cloud Stream)

__Patterns__ : [Asynchronous Messaging for Inter-service Communication](http://microservices.io/patterns/communication-style/messaging.html) 

1. `gs-config-server`: This is cloud config server.
2. `gs-discovery-eureka-server`: This is Eureka server for service registry.
3. `gs-bus-hello-service` and `gs-bus-bye-service`: Just a plain-vanilla microservice to experiment events broadcasting among nodes via Spring Cloud Bus. 
4. `gs-stream-greetings-service`: A simple greetings microservice to try Spring Cloud Stream concepts.

## TODO

* Enable to run via script or docker compose

## Few Awesome Tools to Try

* [Spring Boot Cloud CLI](https://cloud.spring.io/spring-cloud-cli/): Developer Productivity Tool to quickly launch common services like Config Server, Eureka, Zipkin...
* [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin): A simple UI to administrate Spring Boot applications
* [Microservices Dashboard](https://github.com/ordina-jworks/microservices-dashboard): A dashboard to visualize links between microservices and the encompassing ecosystem

## Also Refer

* [Introduction to Microservices](https://www.nginx.com/blog/introduction-to-microservices/)
* [Microservices Architecture Patterns](http://microservices.io/index.html)
* [Spring Cloud Project](http://projects.spring.io/spring-cloud/) & [Spring Cloud Samples](https://github.com/spring-cloud-samples)
* [Netflix OSS](http://netflix.github.io/)
* [Wiring Microservices with Spring Cloud](https://www.infoq.com/articles/spring-cloud-service-wiring)
* [Build self-healing distributed systems with Spring Cloud](http://www.javaworld.com/article/2927920/cloud-computing/build-self-healing-distributed-systems-with-spring-cloud.html)
* [Building Microservices with Spring Cloud and Docker](http://www.kennybastani.com/2015/07/spring-cloud-docker-microservices.html)

## Disclaimer

The code snippet are mainly targeted for learning purpose, and not focused for producing production code quality.