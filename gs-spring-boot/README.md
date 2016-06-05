# Spring Boot - learning made easy

Getting started (gs) with Spring Boot projects.

## Prerequisite

	- Java 1.8+
	- Maven / Gradle
	- Spring STS IDE

## Sample Spring Boot App

* [gs-spring-boot-app](https://github.com/tirthalpatel/Learning-Spring/tree/master/gs-spring-boot/gs-spring-boot-app) - Sample POC code snippet for getting started with Spring Boot Application (manually created maven based project)
* [gs-spring-boot-maven-skeleton](https://github.com/tirthalpatel/Learning-Spring/tree/master/gs-spring-boot/gs-spring-boot-maven-skeleton) - Maven Based Spring Boot Project Skeleton (created using Spring Initializer)
* [gs-spring-boot-gradle-skeleton](https://github.com/tirthalpatel/Learning-Spring/tree/master/gs-spring-boot/gs-spring-boot-gradle-skeleton) - Gradle Based Spring Boot Project Skeleton (created using Spring Initializer)
	
## Known Issues

### Whitelabel Error Page - This application has no explicit mapping for /error, so you are seeing this as a fallback. - There was an unexpected error (type=Not Found, status=404).

After creating Spring boot application using "Spring Initializer", when you open "http://localhost:8080/" in the browser. If you see this error message, then it means either @Controller doesn't exist in your code.

To fix this, just add Controller java class and apply @RestController.

## Disclaimer

The code snippet are mainly targeted for learning purpose, and not focused for producing production code quality.