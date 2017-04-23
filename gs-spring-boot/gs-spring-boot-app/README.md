# Getting started with Spring Boot Application

Manually created maven based project for POC of Spring Boot (1.5.2.RELEASE version) to try following.

* Spring Boot + Spring Web (MVC and REST) + Thymeleaf (Template engine)
* Spring Boot + Swagger UI
* Spring Boot + Properties customization
* Spring Boot + Spring Data JPA + H2 Database + FlywayDB
* Spring Boot + Spring Data REST + HAL browser
* Spring Boot + Spring Data Redis (publish and subscribe to messages sent via Redis) 
* Spring Boot + Production-ready features of Spring Boot Actuator
* Spring Boot + Hawtio (a lightweight web console to monitor and manage application)
* Spring Boot + Developer Tools (for automatic restart, livereload support..)
* Spring Boot + Lombok (a java annotation library to reduce boilerplate code)
* Spring Boot + Testing (JUnit, Hemcrest, AssertJ, Mockito, Spring Test) 

## Prerequisite

- Java 1.8+
- Maven
- Spring STS IDE: [Install Lombok Plugin](https://projectlombok.org/download.html)
- Redis Server: [Install and Start Redis Server](http://redis.io/topics/quickstart) / [Download Redis for Windows](https://github.com/MSOpenTech/redis/tags) / [Start Redis using Docker](https://hub.docker.com/r/library/redis/)
	
## How to setup this project in STS?

* Import -> Maven -> Existing Maven Projects -> Choose project's root folder and Import.

* Fix JRE configuration issues, if any. Project -> Properties -> Ensure following is configured for Java 1.8
	- Java Build Path -> Libraries -> JRE System Library
	- Java Compiler -> Compiler compliance level = 1.8 

## How to run the application?

### Option 1: Manually

* Run Redis server locally: `redis-server`
* Run "App.java" as java application in STS / Run `mvn spring-boot:run -DREDIS_HOST=127.0.0.1 -DREDIS_PORT=6379` maven command on command prompt

### Option 2: Using Docker Compose (Recommended)

* Go to application's root location, for example, `cd \D\...<path>...\gs-spring-boot-app` and use following docker-compose commands	
	- Build image firstly, and then create and start redis and application containers in detached mode: `docker-compose up -d --build`
	- View output from container: `docker-compose logs -f`
	- Start / Stop / Restart / Rebuild services: `docker-compose start` / `docker-compose stop` / `docker-compose restart` / `docker-compose build`	
	- Remove stopped services: `docker-compose rm --all`
	- Stop and remove containers, networks, images, and volumes: `docker-compose down`

### Option 3: Using Docker
 
* Run following docker commands to manage a Redis instance:		
	- Run Redis server container: `docker run -p 127.0.0.1:6379:6379 --name standalone-redis-server -d redis` 					
	- Start / Stop Redis container: `docker start standalone-redis-server` / `docker stop standalone-redis-server`		
	- Run Redis client using docker and connect it with Redis server: `docker run -it --rm --name redis-cli --link standalone-redis-server:redis redis redis-cli -h redis -p 6379 <here-append-any-redis-command>`

* Run following docker commands to manage Getting Started Spring Boot application:
	- Go to application's root location, for example, `cd \D\...<path>...\gs-spring-boot-app`
	- Build an docker image from docker file: `docker build -f gs-spring-boot-app.dockerfile -t gsspringbootapp .`
	- Run container: `docker run -i -p 8080:8080 --name standalone-gs-spring-boot-app-server --link standalone-redis-server:standalone-redis-server -e REDIS_HOST=standalone-redis-server gsspringbootapp` (additional options can be used such as "-v" mount data volume and "-w" working directory inside container)
	- Start container: `docker start -i standalone-gs-spring-boot-app-server`

* Additional tips:
	- To connect Redis sever from client outside docker, use "Host = docker-machine ip and Port = 6379", for example, redis-cli -h <docker-ip> -p 6379  
	- Few useful docker commands: (i) Get an IP address of docker machine: `docker-machine ip` (ii) Show all images: `docker images -a` (iii) Show all containers: `docker ps -a` (iv) Remove an image forcefully: `docker rmi -f <image-id>` (v) Remove container: `docker rm <container-name>`

## Try it 

* If you started application using docker, the consider localhost = docker-machine ip
* Try following urls in browser:
	- `http://localhost:8080/`: You should see "Welcome to the Spring Boot application" message, coming from HomeController.java controller.
	- `http://localhost:8080/test.html`: You should see "Test Html Page - Welcome to the Spring Boot application" text in blue color, coming from test.html.
	- `http://localhost:8080/swagger-ui.html`: You should be able to test REST API using Swagger UI.
	- `http://localhost:8080/h2-console`: You should be able to access H2 database.
	- `http://localhost:8080/api/browser/index.html#/api/messages`: You should be able to try HAL browser and Spring Data REST APIs.
	- `http://localhost:8080/messages.html`: You should be able to see Spring MVC demo page, coming from messageList.html.
	- `http://localhost:8080/health`: You should be able to see application health information.
	- `http://localhost:8080/hawtio/index.html`: You should be able to see a lightweight hawtio web console for the application monitoring.

## Steps to create Spring Boot project manually?

Easiest approach to create Spring Boot project skeleton could be using Spring Initializer or Spring Boot CLI. However, following manual steps also work greatly for getting your hands dirty with Spring Boot.

### Spring Boot +Spring Web (MVC and REST) + Thymeleaf (Template engine)

* STS - Create New Maven Project with default "maven-archetype-quickstart"
	
* Open pom.xml and add Spring boot dependencies: spring-boot-starter-parent + other dependencies as per need like spring-boot-starter-thymeleaf (which internally includes spring-boot-starter-web for Spring MVC and associated dependencies)

		<parent>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-parent</artifactId>
		    <version>1.5.2.RELEASE</version>
		</parent>
		<dependencies>
		  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
		</dependencies>

* Open App.java, added @SpringBootApplication class level annotation and added SpringApplication.run(App.class, args) in main method.

* Add controller package. Create HomeRestController.java with Spring MVC provided @RestController and @RequestMapping annotations. This enables to access REST endpoints using browser, like http://localhost:8080/

* Add "resources/public" folder underneath "src/main" to keep static UI resources of an application. For example, "test.html". This enables to access static resources using browser, like http://localhost:8080/test.html

* Add "resources/templates" folder underneath "src/main" to keep view components. For example, customized "error.html". Try to access page which doesn't exist, like http://localhost:8080/show-invalid-page.html 

* Awesome, Spring Boot based basic project structure is created manually.  

### Spring Boot + Swagger UI

* Open pom.xml and add Swagger dependencies: 

		<dependency>
	        <groupId>io.springfox</groupId>
	        <artifactId>springfox-swagger-ui</artifactId>
	        <version>2.2.2</version>
	        <scope>compile</scope>
	    </dependency>
	    <dependency>
	        <groupId>io.springfox</groupId>
	        <artifactId>springfox-swagger2</artifactId>
	        <version>2.2.2</version>
	        <scope>compile</scope>
	    </dependency>
    
* Create SwaggerConfig.java to enable Swagger.

* This enables to test REST APIs using swagger UI in browser: http://localhost:8080/swagger-ui.html	
	
### Spring Boot + Properties customization
	
* Create application.properties and setup environment profiles (i.e. application-test.properties, application-prod.properties) to customize embedded container port or logging level or any other Spring Boot provided configuration [see](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html).		

* How to run application with environment specific profiles (to load "application-<env>.properties" configuration over "application.properties" file)?
	* STS - Run Configurations - Java Application - App - Add VM Arguments : "-Dspring.profiles.active=prod". For example, production configuration not showing default Spring banner on application startup.	
	* Alternatively, run "mvn spring-boot:run -Dspring.profiles.active=prod" maven command on command prompt. 

### Spring Boot + Spring Data JPA + H2 Database + FlywayDB 

* For enabling database migration, Liquibase is also supported by Spring Boot as an alternative of FlywayDB.

* Add Spring Data JPA, H2 database and FlywayDB dependencies in pom.xml

		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-data-jpa</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>com.h2database</groupId>
	        <artifactId>h2</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.flywaydb</groupId>
	        <artifactId>flyway-core</artifactId>
	    </dependency>

* application.properties - Configure Spring Boot provided h2, datasource, flyway and jpa properties.

* Create "db\migration\" folder underneath resources folder to keep FlywayDB migration SQL scripts.

* Create Message.java (JPA entity) and MessageRepository.java (extending JpaRepository of Spring Data JPA).

* Create MessageService.java, which communicates with persistence layer using MessageRepository. 

* Create MessageRestController.java, which uses MessageService. Ready to test these Message CRUD REST APIs using swagger UI in browser: http://localhost:8080/swagger-ui.html

* Create messageList.html and MessageMvcController.java using Thymeleaf and Spring MVC. Try in browser: http://localhost:8080/messages.html 

* Done with fundamental end-to-end flow (layered architecture), from UI to Controller to Service to Repository to DB.

### Spring Boot + Spring Data REST + HAL browser

* For easily building hypermedia-driven REST web services on top of Spring Data repositories

* Add Spring Data REST and optional HAL browser tool dependencies in pom.xml

		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>	
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-rest-hal-browser</artifactId>
		</dependency>

* Configure "spring.data.rest.basePath" in "application.properties"

* Start using Spring REST DATA, such as applied @RepositoryRestResource in MessageRepository.java

* Try HAL browser: http://localhost:8080/api

* Try REST services exposed by Spring Data REST: http://localhost:8080/api/messages

### Spring Boot + Spring Data Redis (publish and subscribe to messages sent via Redis)

* Spring Data Redis, part of the larger Spring Data family, provides easy configuration and access to Redis from Spring applications

* Add Spring Data Redis dependency in pom.xml
	
		<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
      </dependency>

* Optionally, Spring Boot's default redis properties can be overrided in application.properties such as redis server hostname and port 

* Example code that uses StringRedisTemplate to publish a string message and has a POJO subscribe for it using MessageListenerAdapter
	- RedisConfig.java: Configuration for a connection factory, message listener container and redis template
	- MessageReceiver.java: A POJO Redis message receiver
	- MessageService.java: Publishing message to given redis channel

* See it in action by adding new message using 'http://localhost:8080/messages.html' and see log messages in console

### Spring Boot + Production-ready features of Spring Boot Actuator

* Add Spring Boot Actuator dependency in pom.xml

		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-actuator</artifactId>
	    </dependency>

* [Actuator endpoints allow to monitor and interact with the application](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html), for example, 
	- http://localhost:8080/health : Shows application health information
	- http://localhost:8080/metrics : Shows metrics information for the current application
	- http://localhost:8080/flyway : Shows any Flyway database migrations that have been applied

### Spring Boot + Hawtio (a lightweight web console to monitor and manage application)

* Add Hawtio dependencies in pom.xml (it internally expects that "spring-boot-starter-actuator" is added too)

		<dependency>
			<groupId>io.hawt</groupId>
			<artifactId>hawtio-springboot</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>io.hawt</groupId>
			<artifactId>hawtio-core</artifactId>
			<version>1.5.0</version>
		</dependency>

* Create HawtioConfig.java to enable Hawtio.

* This enables to use Hawtio web console via browser: localhost:8080/hawtio/index.html

### Spring Boot + Developer Tools (for automatic restart, livereload support, etc.)

* Add Spring Boot Developer Tools dependencies in pom.xml

	    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>

* This offers following capabilities:
	- Automatic restart: Restarts a running application when files are changed in the classpath
	- LiveReload support: Changes to resources trigger a browser refresh automatically (Need to install the LiveReload plugin into web browser to use this feature)
	- Remote development: Supports automatic restart and LiveReload when deployed remotely
	- Development property defaults: Provides sensible development defaults for some configuration properties

* The developer tools will be disabled when your application is running from a fully packaged JAR or WAR file, so it’s unnecessary to remove this dependency before building a production deployment.

### Spring Boot + Lombok (a java annotation library to reduce boilerplate code)

* Add Lombok dependencies in pom.xml
	
		<dependency>	
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>

* Start using [Lombok features](https://projectlombok.org/features/). For example, see Message.java not having traditional boilerplate code 

### Spring Boot + Testing (JUnit, Hemcrest, AssertJ Mockito, Spring Test) 

* Add Testing dependencies in pom.xml

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

* Create application-test.properties file.
    
* Create few unit tests. 
	- 'JunitHemcrestAssertjDemoTest.java': Demo of JUnit, Hamcrest and AssertJ usage
	- 'MockitoDemoTest.java': Demo of Mocking using Mockito without leveraging Spring Boot's @MockBean
	- 'MockMvcDemoTest.java': Demo of Spring MVC Controller testing using @WebMvcTest and @MockBean
	- 'TestRestTemplateDemoTest.java': Demo of integration testing using WebEnvironment.RANDOM_PORT and TestRestTemplate (a convenience alternative to Spring’s RestTemplate that is useful in integration tests)

* How to run test cases?
	- STS: select project or junit package or test class, right click and run as "JUnit tests" / "Maven test" by passing "-Dspring.profiles.active=test" in VM arguments		
	- Alternatively, run maven command from root of project: "mvn test -Dspring.profiles.active=test"