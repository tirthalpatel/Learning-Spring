# Cloud Config Client

Demo project for Spring Cloud Config Client to use features of Cloud Config Server.

A Spring Boot application can take immediate advantage of the Spring Config Server (or other external property sources provided by the application developer).

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Config Client', 'Spring Web' and 'Spring Actuator' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-config-client.properties', 'gs-config-client-dev.properties'
3. Started the application and status could be validated using `http://localhost:7001/admin/info` / `http://localhost:7001/admin/health` url.
4. Added 'ExtConfigRestController.java' to try externalized property configuration context hierarchy, refresh scope, encryption/decryption, etc.
5. Added 'GsDemoSettings.java' to read properties with prefix 'gs.demo' and included 'spring-boot-configuration-processor' dependency in pom.xml: usage of '@ConfigurationProperties'

### Try
1. Override default configured port by passing argument: `-DPORT=7004`
2. Bootstrap and Application Context Hierarchy (see 'message' property and its priority order is - from 'gs-config-client.properties' to 'application.properties of config repo' 'application.properties of client app' to 'bootstrap.properties': `http://localhost:7001/admin/env` 
3. Change 'message' property values to understand priority order of configuration files (as mentioned in previous point): `http://localhost:7001/message`	
4. Don't restart server after each changes in external config repo. Rather, thanks to @RefreshScope which refreshes values, if POST to /refresh endpoint: `http://localhost:7001/admin/refresh`
5. Config server's encrypt / decrypt feature - Refer 'secret.password' in 'gs-config-client.properties': `http://localhost:7001/admin/env` and `http://localhost:7001/password` shows usage of `{cipher}`
6. Config server's 'spring.cloud.config.server.overrides' feature - Refer 'spring.cloud.config.server.overrides.gs.demo.try-to-overide-me' in 'application.properties' of config repo: See `http://localhost:7001/admin/env` for usage of `spring.cloud.config.server.overrides`
7. Config client fail fast - Stop 'gs-config-server' and start 'gs-config-client': Usage of `spring.cloud.config.failFast=true` fails startup of a service, if it cannot connect to the Config Server
8. Check profile - Start 'gs-config-client' with 'dev' profile: see `http://localhost:7002/admin/env` and `http://localhost:7002/password`