# Cloud Config Server

Demo project for Spring Cloud Config Server (which offers central management for configuration via a git or svn backend). 

The Server provides an HTTP, resource-based API for external configuration (name-value pairs, or equivalent YAML content). The server is easily embeddable in a Spring Boot application using the @EnableConfigServer annotation. 

Also refer [Externalized Configuration Pattern](http://microservices.io/patterns/externalized-configuration.html)

## Pre-requisites

* [Parent README.md](../README.md)
* Ensure to configure `spring.cloud.config.server.git.uri` correctly in `src/main/resources/application.properties`
* [Install Java Cryptography Extension](http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_encryption_and_decryption) to use Encryption and Decryption features

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Config Server' dependency.
2. Created 'gs-config-repo' folder in parent directory to preserve centralized configuration (Ideally could be separate repo) and added 'application.properties' ('server.port', 'info.id')
3. Configured 'application.properties' in 'gs-config-server': 'spring.application.name', 'info.id', 'server.port', 'spring.cloud.config.server.git.uri', 'spring.cloud.config.server.git.search-paths'
4. Added '@EnableConfigServer' in 'GsConfigServerApplication.java'. 
5. Started the application and status could be validated using `http://localhost:8888/admin/health` url.
6. Added 'encrypt.key' in 'application.properties' of 'gs-config-server' to support encryption and decryption features

### Try

1. `http://localhost:8888/admin/env`
2. `http://localhost:8888/app/profile` | `http://localhost:8888/gs-config-client/test` | `http://localhost:8888/gs-config-client.properties` ...
3. `http://localhost:8888/encrypt` | `http://localhost:8888/decrypt` [using POST method]

### Spring Cloud Config - Encrypt / Decrypt - Problem and Solution

* Problem: No key was installed for encryption service ---> Solution: Add "encrypt.key" in application.properties

* Problem: java.security.InvalidKeyException: Illegal key size ---> Solution: [Install Java Cryptography Extension](http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_encryption_and_decryption)
