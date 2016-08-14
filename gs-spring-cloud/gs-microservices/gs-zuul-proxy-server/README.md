# Zuul Proxy Server

Demo project for using Spring Cloud Netflix Zuul for intelligent routing, filtering and server side load balancing.

Spring Cloud has created an embedded Zuul proxy to ease the development of a very common use case where a UI application wants to proxy calls to one or more back end services. The proxy uses Ribbon to locate an instance to forward to via discovery, and all requests are executed in a hystrix command, so failures will show up in Hystrix metrics, and once the circuit is open the proxy will not try to contact the service.

NOTE: The Zuul starter does not include a discovery client by default, so for routes based on service IDs one must provide one of those on the classpath as well (e.g. Eureka is one choice). 

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server
	- [gs-xxng-service](../gs-xxng-service/README.md) followed by [gs-xxng-client](../gs-xxng-client/README.md): For accessing backend services via Zuul router and filters

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Zuul', 'Config Client' and 'Eureka Discovery' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-zuul-proxy-server.properties'.
3. Added 'bootstrap.properties' in project resources to locate config server.
4. Added '@EnableZuulProxy' in 'GsZuulProxyServerApplication.java' to enable embedded Zuul reverse proxy, so it forwards local calls to the appropriate service.
5. Added Zuul related configuration in 'gs-config-repo/gs-zuul-proxy-server.properties'.
6. Started the application and status could be validated using `http://localhost:8766/admin/info` / `http://localhost:8766/admin/health` url.

### Try

1. Zuul's route endpoint (showing automatically discovered ServiceIds from Eureka excluding configured in 'zuul.ignored-services' + custom configured 'zuul.routes'): `http://localhost:8766/admin/routes`
2. Don't allow to access server specific services such as Eureka server, Zuul itself and Hystrix Dashboard using '/api' prefix via Zuul (as configured in 'zuul.ignored-services'): `http://localhost:8766/api/gs-hystrix-dashboard/hystrix`
3. Don't allow to access admin service endpoints of other services via Zuul (as configured in 'zuul.ignoredPatterns'): `http://localhost:8766/api/gs-xxng-service/admin/info`
4. Access automatically discovered ServiceIds from Eureka via Zuul: `http://localhost:8766/api/gs-xxng-service/ting` / `http://localhost:8766/api/gs-xxng-client/xxng`
5. Access custom configured routes via Zuul (as configured in 'zuul.routes'): `http://localhost:8766/api/xingo/xxng`