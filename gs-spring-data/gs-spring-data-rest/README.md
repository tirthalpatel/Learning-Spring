# Getting Started with Spring Data REST

Sample code snippet for getting hands dirty with Spring Data REST and Spring Data JPA running on Spring Boot. This code is modified version of [dlbunker/ps-guitar-rest](https://github.com/dlbunker/ps-guitar-rest) repository with some additional code.

## Prerequisites

* Git (http://git-scm.com/)
* JDK 1.8+ (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven 3.x+ (http://maven.apache.org/)
* An IDE of your choice (Eclipse, IntelliJ, Spring STS, etc.)

## Getting Started

To run this project locally, perform the following steps.

* Download / Clone this project to your local machine. 
* Import "gs-spring-data-rest" project in STS using an existing maven project and switch to Spring perspective. Fix build path errors, such as JRE configuration, in case exists.
* Run the JUnit tests in the src/test/java folder.  If all pass you are good to go.
* Next try running the app as a Spring Boot app.  You can do this by running the Main.java file in this project as a standard java project or you can run spring boot at your project's root with Maven using the following command: 'mvn spring-boot:run'
* Play with exposed REST APIs using Spring Data REST HAL Browser Tool @ [http://localhost:8080/api](http://localhost:8080/api). Try GET, POST, PUT, PATCH and DELETE. For example,
	- GET: /api/modelTypes
	- POST: /api/modelTypes
	- GET: /api/modelTypes/{id}
	- PUT: /api/modelTypes/{id}
	- PATCH: /api/modelTypes/{id}
	- DELETE: /api/modelTypes/{id}	
* Alternatively, one may use any choice of REST client tool such as Postman.

## Key Notes

* When every time Spring Boot project starts, for unit testing purpose, the project is configured to delete all data and create sample data from "data.sql".
* How to install Spring Data REST in the Spring Boot project? 
	- pom.xml: Need to add "spring-boot-starter-data-rest" dependency + Optionally enable HAL browser by adding "spring-data-rest-hal-browser" dependency
* How to customize default URI of services?
	- application.properties: Configure "spring.data.rest.basePath"
	- Use @RepositoryRestResource, to customize parameters of given service. For example, see @RepositoryRestResource in ManufacturerJpaRepository.java, which changes default "http://localhost:8080/manufacturers" service url to the customized "http://localhost:8080/api/mfgs" URL
	- Disable given service using @RepositoryRestResource(exported=false). For example, see in LocationJpaRepository.java, which returns 404 when accessing "http://localhost:8080/api/locations"
* How to customize REST Payloads?
	- Use @Projection and ensure to create interfaces of custom REST Payload inside the model or entity package or sub-package, for example, see com.tirthal.learning.model.projections package
	- In general, custom getter methods name in Projection interface must match with attributes of entity types, for example, see ModelDetail.java. Now access "http://localhost:8080/api/models/1" to see default Payload vs. "http://localhost:8080/api/models/1?projection=modelDetail" to see customized Payload
	- What if, there is a need to give different name for JSON attributes than Entity attributes? Use @Value and Spring EL. For example, see getModelName() in ModelDetailView.java. Access "http://localhost:8080/api/models/1?projection=modelDetailView" to see customized Payload
	- What if, there is a need to customize default payload of repository itself? Use @RepositoryRestResource annotation for Projection Excerpts. For example, see ModelJpaRepository.java. Access "http://localhost:8080/api/models" to see customized Payload
	- What if, need to ignore any field of entity, so that it doesn't go in JSON Payload (e.g. password). Use @JsonIgnore of Jackson JSON Parser.
	
	 		 
	 	 
## Reference

* [Getting Started with Spring Data REST](https://app.pluralsight.com/library/courses/spring-data-rest-getting-started/table-of-contents)
