# Spring Cloud Version Upgrade

See [Spring Cloud - Release Trains](https://spring.io/projects/spring-cloud#overview)

## Spring Cloud Upgrade from Dalston.SR1 to Greenwich.RELEASE

Release notes:
* [Spring Cloud Edgware](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Edgware-Release-Notes)
* [Spring Cloud Finchley](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Finchley-Release-Notes)
* [Spring Cloud Greenwich](https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Greenwich-Release-Notes)

### Maven - pom.xml

Renamed starters in Spring Cloud Edgware

* spring-boot-starter-parent version: `1.5.2.RELEASE -> 2.1.2.RELEASE`
* spring-cloud-dependencies version: `Dalston.SR1 -> Greenwich.RELEASE`
* Eureka server artifact id changed: `spring-cloud-starter-eureka-server -> spring-cloud-starter-netflix-eureka-server`
* Eureka client artifact id changed: `spring-cloud-starter-eureka -> spring-cloud-starter-netflix-eureka-client`
* Hystrix artifact id changed: `spring-cloud-starter-hystrix -> spring-cloud-starter-netflix-hystrix`
* Hystrix Dashboard artifact id changed: `spring-cloud-starter-hystrix-dashboard -> spring-cloud-starter-netflix-hystrix-dashboard`
* Turbine artifact id changed: `spring-cloud-starter-turbine -> spring-cloud-starter-netflix-turbine`
* Zuul artifact id changed: `spring-cloud-starter-zuul -> spring-cloud-starter-netflix-zuul`

### Zipkin Stream server is deprecated and removed

* Starting from the Edgware release, the Zipkin Stream server is deprecated 
* In the Finchley release, the Zipkin Stream server got removed
* See [(1)](https://cloud.spring.io/spring-cloud-sleuth/multi/multi__zipkin_stream_span_consumer.html), [(2)](https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin), [(3)](https://zipkin.io/pages/quickstart.html)

### Spring Boot 2.0 Migration Guide

* Property name changed for customizing the management endpoint paths of Spring Boot Actuator: `management.contextPath -> management.endpoints.web.base-path`
* The majority of Web endpoints are now disabled by default (Only the /health and /info endpoints are exposed) and the `management.security.enabled property has been removed`
* [See](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide)


