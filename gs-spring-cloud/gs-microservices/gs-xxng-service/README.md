# Xing Xong MicroService

Demo project of Xing Xong MicroService (e.g. Input -> Output = Xing -> Xong, Ping -> Pong, Ting -> Tong, ding -> dong, king -> kong...), which leverages Spring Cloud stack

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Eureka Discovery', 'Config Client', 'Spring Web', and 'Spring Actuator' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-xxng-service.properties'.
3. Added 'bootstrap.properties' in project resources to locate config server.
4. Added '@EnableDiscoveryClient' and 'XingXongRestController' in 'GsXingXongServiceApplication.java'.
5. Started the application and status could be validated using `http://localhost:7002/admin/info` / `http://localhost:7002/admin/health` url.

### Try

1. Try me: `http://localhost:7002/ping` / `http://localhost:7002/ding` / `http://localhost:7002/ting`
2. Try running more than one instances of this service and see how those are automatically registered with Eureka server: `http://localhost:8761/admin/registry`