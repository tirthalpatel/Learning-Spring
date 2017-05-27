# Zipkin Server

This is zipkin server for visualizing and querying traces, which are received over HTTP from `gs-games-*` microservices (enabled for automatic instrumentation of traces using Spring Cloud Sleuth).

Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in microservice architectures. It manages both the collection and lookup of this data. Zipkin’s design is based on the Google Dapper paper.

Spring Cloud Sleuth implements a distributed tracing solution for Spring Cloud. It also borrows Dapper’s terminology. 

What is Automatically Instrumented, if Sleuth is added/enabled in microservice project? At the same time, something can be disabled through configuration, for instance, spring.sleuth.zuul.enabled=false in case of disabling Zuul support.
- Runnable / Callable operations
- Spring Cloud Hystrix, Zuul
- RxJava
- Synchronous / Asynchronous RestTemplate
- Spring Integration
- @Async, @Scheduled operations

There are essentially two steps (1) Enable microservice project for automatic instrumentation using Sleuth (2) Consider choice of log aggregation tools for visualizing and querying traces such as Zipkin, Kibana, Splunk, etc

Refer [Distributed Tracking Pattern](http://microservices.io/patterns/observability/distributed-tracing.html)

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server
	- [gs-games-catalog-service](../gs-games-catalog-service/README.md): Just a plain-vanila games catalog microservice and also enabled for automatic instrumentation of traces.
	- [gs-games-review-service](../gs-games-review-service/README.md): Just a plain-vanila games reviews microservice and also enabled for automatic instrumentation of traces.
	- [gs-games-recommendation-service](../gs-games-recommendation-service/README.md): Just a plain-vanila games recommendations microservice and also enabled for automatic instrumentation of traces.
	- [gs-games-detail-rxjava-gateway](../gs-games-detail-rxjava-gateway/README.md): This is demo of API Gateway using RxJava for concurrent API aggregation and transformation of `gs-games-catalog-service`, `gs-games-recommendation-service` and `gs-games-review-service`. Also, enabled for automatic instrumentation of traces.

## Key Notes

(1) Enabled `gs-games-*` microservice projects for automatic instrumentation using Sleuth and sending traces to Zipkin server over HTTP: see added dependencies in `gs-games-detail-rxjava-gateway\pom.xml` and configuration in `gs-config-repo\gs-games-detail-rxjava-gateway.properties`
(2) Created `gs-zipkin-server` project using SPRING INITIALIZR. Selected 'Zipkin UI' and 'Zipkin Server' dependencies. Added `@EnableZipkinServer` annotation in 'GsZipkinServerApplication.java'. Added 'bootstrap.properties' in project resources to locate config server.
(3) Added property file in 'gs-config-repo' folder: 'gs-zipkin-server.properties'.
(4) Started the zipkin server and status could be validated using `http://localhost:9411/` url.

### Try

(1) Invoke REST API of any 'gs-games-*' microservices (where Sleuth is enabled): for example, `http://localhost:6001/game/G100` from 'gs-games-detail-rxjava-gateway'
(2) Check 'gs-games-*' microservices logs: notice the [appname,traceId,spanId,exportable] entries, in which exportable=true means logs are exported to Zipkin
(3) Open zipkin UI and play with 'Find a trace' / 'Dependencies' options : `http://localhost:9411/`