# Getting Started with Spring JPA and Spring Data JPA

Sample code snippet for getting hands dirty with standard Spring JPA and Spring Data JPA using an H2 DB (without Spring Boot). This code is modified version of [dlbunker/ps-guitar-db](https://github.com/dlbunker/ps-guitar-db) repository with some additional code.

## Prerequisites

* Git (http://git-scm.com/)
* JDK 1.8+ (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven 3.x+ (http://maven.apache.org/)
* An IDE of your choice (Eclipse, IntelliJ, Spring STS, etc.)

## Getting Started

To run this project locally, perform the following steps.

* Download / Clone this project to your local machine. 
* Import "gs-spring-data-jpa" project in STS using an existing maven project and switch to Spring perspective. Fix build path errors, such as JRE configuration, in case exists.
* Run the JUnit tests in the src/test/java folder.  If all pass you are good to go.

## Key Notes

* Spring Data JPA Features
	- Enhances standard JPA with Spring
	- Simplifies Data Access Layer code
	- Offers Repository generator and gives full control to incorporate custom needs
	- Query DSL
	- Paging, Auditing, Locking, etc.	
* How to install Spring Data JPA in the project? 
	- pom.xml: Need to add "spring-data-jpa" dependency
	- In Spring's app-context.xml, add <jpa:repositories base-package="com.tirthal.learning.repository" /> / Use @EnableJpaRepositories
* JPA entities code is underneath "src\main\java\com\tirthal\learning\model" folder
* Standard Spring Repositories 
	- Refer code underneath "src\main\java\com\tirthal\learning\repository" and "src\test\java\com\tirthal\learning" folders
* Spring Data JPA Repositories
	- Not necessary to add @Repository annotation, as "<jpa:repositories..." configuration scans JPA Repositories. However, one may still apply @Repository stereotype for consistency
	- Refer code underneath "src\main\java\com\tirthal\learning\repository\springdata" and "src\test\java\com\tirthal\learning\springdata" folders
	- Custom Repository sample code: ModelJpaRepositoryCustom.java and ModelJpaRepositoryImpl.java
* Compare code of "Standard Spring Repositories" vs. "Spring Data JPA Repositories"

## Reference

* [Getting Started with Spring Data JPA](https://app.pluralsight.com/library/courses/spring-data-jpa-getting-started/table-of-contents)