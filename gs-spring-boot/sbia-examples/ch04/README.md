## Testing with Spring Boot

* spring-boot-starter-test, which includes
	- Junit: For all unit testing needs
	- Hamcrest: Matching and assertions (declarative, readable matching rules)
	- Mockito: Mock objects and verify
	- Spring Test: Testing tools and integration testing support
* Integration Testing
	- @RunWith(SpringJUnit4ClassRunner.class): Helps to load a Spring application context in JUnit-based application tests
	- @SpringApplicationConfiguration: Replaces @ContextConfiguration when writing tests for Spring Boot applications to load Spring application context
* Web Integration Testing
	- @WebIntegrationTest: Starts an embedded servlet container to support calling REST API
