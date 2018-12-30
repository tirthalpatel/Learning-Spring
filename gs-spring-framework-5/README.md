# Getting Started with Spring Framework 5.x

Spring Framework 5.0 is the first major release of the Spring Framework since version 4 was released in December of 2013.

What's New in Spring Framework 5.x? See [(1)](https://spring.io/blog/2017/09/28/spring-framework-5-0-goes-ga), [(2)](https://github.com/spring-projects/spring-framework/wiki/What%27s-New-in-Spring-Framework-5.x) and [(3)](https://dzone.com/articles/whats-new-in-spring-framework-5).

## Required softwares to play with example code

* Java 8 or later
* STS / Intellij IDE
* Maven, Gradle

## JDK Baseline Update for Spring Framework 5.x

* The entire Spring framework 5.x codebase runs on Java 8
* __Java 8 is the minimum requirement__ to work on Spring Framework 5.x
* Spring Framework 5.0.x 
	* Full compatibility with JDK 9 for development and deployment
	* Compatibility with Java EE 8 API level at runtime (e.g. Servlet 4.0)
	* Spring Framework 5.0.x : JDK 8-10
* __Spring Framework 5.1.x : JDK 8-12__

## Core Framework Revision

* __Non-null API declaration at the package level__: @Nullable and @NotNull annotations will explicitly mark nullable arguments and return values. This enables dealing null values at compile time rather than throwing NullPointerExceptions at runtime.
* __Selective declarations of Java 8 default methods in core Spring interfaces__ 
	* See code in __default-methods-example__ maved project
* Spring Framework 5.0 comes with its own Commons __Logging bridge out of the box__
	* __spring-jcl__ instead of standard Commons Logging; still excludable/overridable
	* Autodetecting Log4j 2.x, SLF4J, JUL (java.util.logging) without any extra bridges
	* See code in __logging-example__ maved project

## Core Container Updates

* __Support for candidate component index (as alternative to classpath scanning)__
	* Reading entities from the index rather than scanning the classpath does not have significant differences for small projects (e.g. with less than 200 classes). However, it has significant impacts on large projects. Loading the component index is cheap. Therefore, the startup time with the index remains constant as the number of classes increases.
	* See @ [SPR-11890](https://jira.spring.io/browse/SPR-11890)
	* Use __spring-context-indexer__ (can also be used with Spring Boot): `mvn clean compile package` will generate `spring.components` index file underneath `classes/target/META-INF`
		```
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-indexer</artifactId>
			<optional>true</optional>
		</dependency>
		```

## Functional Programming With Kotlin

* Introduces support for JetBrains Kotlin language
* In Spring Framework 5.0, you can write clean and idiomatic Kotlin code for Web functional API like this:
	```
	{
	("/movie" and accept(TEXT_HTML)).nest {
		GET("/", movieHandler::findAllView)
		GET("/{card}", movieHandler::findOneView)
	}
	("/api/movie" and accept(APPLICATION_JSON)).nest {
		GET("/", movieApiHandler::findAll)
		GET("/{id}", movieApiHandler::findOne)
	}
	}
	```
* See code in __kotlin-spring-example__ gradle project
	* Run `gradle wrapper` followed by `.\gradlew bootRun` | `.\gradlew.bat bootRun` 
	* Call `http://localhost:8080/greeting?name=Tirthal` REST service using browser or any REST client tool

## Reactive Programming Model with Spring WebFlux

* Spring Framework 5.0 has a new spring-webflux module, an alternative to spring-webmvc 
	* Built on a reactive foundation - fully asynchronous and non-blocking, intended for use in an event-loop execution model vs traditional large thread pool with thread-per-request execution model.
	* Supports reactive HTTP and WebSocket clients. 
	* Also provides support for reactive web applications running on servers which include REST, HTML, and WebSocket-style interactions.
* A WebClient implementation of a REST endpoint in Spring 5.0 is as follows:
	```
	WebClient webClient = WebClient.create();
	Mono person = webClient.get()
					.uri("http://localhost:8080/movie/42")
					.accept(MediaType.APPLICATION_JSON)
					.exchange()
					.then(response -> response.bodyToMono(Movie.class));
	```
* Use __spring-boot-starter-webflux__ dependency
* See code in __reactive-webflux-example__ gradle project
	* Run with maven: `mvn spring-boot:run`
	* Try: `http://localhost:8080/persons` | `http://localhost:8080/persons/1`

## Testing Improvements

* Complete support for JUnit 5's Jupiter programming and extension models in the Spring TestContext Framework
* Support for parallel test execution in Spring TestContext Framework
* For the reactive programming model, spring-test now includes WebTestClient for integrating testing support for Spring WebFlux
* See `PersonWebIntegrationTest.java` code in __reactive-webflux-example__ gradle project