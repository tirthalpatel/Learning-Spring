# Getting Started with Microservices using Spring Cloud

## Prerequisite

- Java 1.8+
- Maven
- Spring STS IDE

## How to run projects?

All projects are Spring Boot and Maven based.
* Using maven: `mvnw spring-boot:run` / `mvn spring-boot:run`
* Using Boot Dashboard in STS
* Build the application and run it as jar file: `mvn clean install` followed by `java -jar target/<artifactId>-1.0.jar` 

## Run projects in following order

1. `gs-config-server`: This is cloud config server, which internally refers configurations in `gs-config-repo`.
2. `gs-config-client` (Optional): This is demo client project leveraging cloud config server features.
3. `gs-discovery-eureka-server`: This is Eureka server for service discovery.
