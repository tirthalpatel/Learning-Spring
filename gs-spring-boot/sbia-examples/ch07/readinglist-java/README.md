## Leveraging production-ready features of Spring Boot Actuator

* spring-boot-starter-actuator
* Actuator web endpoints
	- http://localhost:8080/beans
	- http://localhost:8080/env
	- http://localhost:8080/health
	- http://localhost:8080/metrics
* Customizing the Actuator 
	- Renaming endpoints via "application.yml"
	- Enabling and disabling endpoints via "application.yml"
	- Defining custom metrics and gauges, see "ReadingListController.java", "ApplicationContextMetrics.java"
	- Creating a custom repository for storing trace data, see "MongoTraceRepository.java", 
	- Plugging in custom health indicators, see "AmazonHealth"
