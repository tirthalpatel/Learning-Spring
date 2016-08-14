# Xing Xong Client

Demo client project, which consumes Xing Xong service and randomly returns twin words such as ting tong, ding dong, ping pong, xing xong, etc. Try your luck. If you get "Xing Xong", then you win.

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery 
* Using Ribbon with Eureka for Routing and Client Side Load Balancing
* Spring Cloud Netflix Hystrix Client for Fault Tolerance with Circuit Breaker Pattern

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server
	- [gs-xxng-service](../gs-xxng-service/README.md): For consuming Xing Xong service

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Eureka Discovery', 'Hystrix', 'Config Client', 'Spring Web', and 'Spring Actuator' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-xxng-client.properties'.
3. Added 'bootstrap.properties' in project resources to locate config server.
4. Added '@EnableDiscoveryClient' and '@Bean for RestTemplate' in 'GsXxngClientApplication.java'.
5. Added 'XxngRestController.java', which internally consumes Xing Xong service either directly (using @Primary RestTemplate) or via Eureka server using Ribbon (using @LoadBalanced RestTemplate)
6. Added '@EnableCircuitBreaker' in 'GsXxngClientApplication.java' and '@HystrixCommand' in 'XxngRestController.java' for circuit breaker pattern
7. Started the application and status could be validated using `http://localhost:7005/admin/info` / `http://localhost:7005/admin/health` url.

### Try

1. Run two or more instances of Xing Xong service (i.e. gs-xxng-service) and try (3) and (4)
2. Run two or more instances of Xing Xong service, stop default instance with port '7002' and try (3) and (4)
3. Try Spring RestTemplate as Load Balanced (internally accessing Xing Xong service via Eureka using Ribbon): `http://localhost:7005/xxng` / `http://localhost:7005/xxng?lb=true`
4. Try Spring RestTemplate as Primary (internally accessing Xing Xong service endpoint directly): `http://localhost:7005/xxng?lb=false`
5. See Ribbon related logs in 'gs-xxng-client' console: `Flipping property: gs-xxng-service.ribbon.ActiveConnectionsLimit to use NEXT property` and `DynamicServerListLoadBalancer for client gs-xxng-service initialized: DynamicServerListLoadBalancer`
6. Stop all Xing Xong service instances and see how fallback can happen via Hystix (circuit breaker pattern): `http://localhost:7005/xxng?lb=true` / `http://localhost:7005/xxng?lb=false`
7. See Hystrix metrics stream via exposed management endpoint (i.e. /hystrix.stream). Although directly not much useful for monitoring purpose, but worth using with Hystrix Dashboard: `http://localhost:7005/admin/hystrix.stream`