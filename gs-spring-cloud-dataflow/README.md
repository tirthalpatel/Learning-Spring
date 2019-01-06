# Getting Started with Spring Cloud Data Flow (v1.7.2.RELEASE)

[Spring Cloud Data Flow](https://cloud.spring.io/spring-cloud-dataflow/) is a toolkit for building data integration and real-time data processing pipelines.

Pipelines consist of Spring Boot apps, built using the Spring Cloud Stream or Spring Cloud Task microservice frameworks. This makes Spring Cloud Data Flow suitable for a range of data processing use cases, from import/export to event streaming and predictive analytics.

## Spring Cloud Data Flow Local Server Setup

### Prerequisite

* Install Docker Compose

### Quick Start Dev Environment using Docker Compose

* Downloaded [spring-cloud-dataflow/v1.7.2.RELEASE: docker-compose.yml](https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/v1.7.2.RELEASE/spring-cloud-dataflow-server-local/docker-compose.yml) and did following [docker compose customizations](https://docs.spring.io/spring-cloud-dataflow/docs/current/reference/htmlsingle/#getting-started-customizing-spring-cloud-dataflow-docker):

	- To use MySQL rather than the H2 embedded database 
	- To allow MySQL port access to other services started by docker compose and also mysql client on host machine (using "mysql:ports" instead of "mysql:expose")
	- To enable the scheduling feature
	- To enable analytics using redis as a backend
	- To enable app starters registration directly from the host machine
	
* Optional step: Configure `~/.m2:/root/.m2` volume of `services:dataflow-server:volumes` in `docker-compose.yml` to map maven's folder as per your host machine

* Open docker terminal and use below commands to get started with dev environment of Spring Cloud Data Flow:

	- Go to project directory: `cd <path>/gs-spring-cloud-dataflow`
	
	- Pull kafka, zookeeper, mysql, redis and dataflow-server docker images: `DATAFLOW_VERSION=1.7.2.RELEASE docker-compose pull`
	
	- Start the Quick Start environment (create and start containers): `DATAFLOW_VERSION=1.7.2.RELEASE docker-compose up`
	
	- Stop and Start the services: `DATAFLOW_VERSION=1.7.2.RELEASE docker-compose stop` | `DATAFLOW_VERSION=1.7.2.RELEASE docker-compose start`
	
	- Destroy the Quick Start environment (stop and remove containers, networks, images, and volumes): `DATAFLOW_VERSION=1.7.2.RELEASE docker-compose down`

## Getting started with Spring Cloud Data Flow Dashboard

Spring Cloud Data Flow provides a browser-based GUI called the dashboard.

* Dashboard URL: `http://<docker-machine-ip>:9393/dashboard` (check ip using this command: `docker-machine ip`)
* Experiment it as per the [docs](http://docs.spring.io/spring-cloud-dataflow/docs/1.7.2.RELEASE/reference/htmlsingle/#dashboard)

### Create and Deploy Stream using [Spring Cloud Stream App Starters](http://cloud.spring.io/spring-cloud-stream-app-starters/)

Spring Cloud Stream Application Starters are Spring Boot based Spring Integration applications that provide integration with external systems. Spring Cloud Stream Applications can be used with Spring Cloud Data Flow to create, deploy, and orchestrate message-driven microservice applications.

#### Plain-vanilla Ticktock Example (time stream source -> log sink)

* Dashboard -> Streams -> Create a stream - Enter below stream definition -> Click Create Stream -> Enter Name: "ticktock" -> Select "Deploy Streams" -> Click "Create and deploy the stream"
	```
	time --time-unit=SECONDS --initial-delay=1 --date-format=HH:mm:ss | log --expression='"Ticktock payload:".concat(payload)' --level=INFO
	```
* Runtime -> Click 'ticktock.log' AppId -> copy "stdout-log-path"
* On another docker terminal, see Ticktock payload stream logs: 
	```
	docker exec -it dataflow-server tail -f <stdout-log-path>
	``` 
* Streams -> Select "ticktock" -> Destroy Streams
	
### Create and Deploy Task using [Spring Cloud Task App Starters](http://cloud.spring.io/spring-cloud-task-app-starters/)

Spring Cloud Task Application Starters are Spring Boot applications that may be any process including Spring Batch jobs that do not run forever, and they end/stop at some point. Spring Cloud Task Applications can be used with Spring Cloud Data Flow to create, deploy, and orchestrate short-lived data microservices.

## References

### Spring Cloud Data Flow

* [Spring Cloud Data Flow v1.7.2.RELEASE doc]( http://docs.spring.io/spring-cloud-dataflow/docs/1.7.2.RELEASE/reference/htmlsingle/)
* [Spring Cloud DataFlow Github Repo](https://github.com/spring-cloud/spring-cloud-dataflow)
* [Spring Cloud DataFlow Samples Github Repo](https://github.com/spring-cloud/spring-cloud-dataflow-samples)

### Spring Cloud Stream and App Starters

* [Spring Cloud Stream Samples Github Repo](https://github.com/spring-cloud/spring-cloud-stream-samples)
* [Spring Cloud Stream Starters Github Repo](https://github.com/spring-cloud-stream-app-starters)

### Spring Cloud Task and App Starters

* [Spring Cloud Task Samples Github Repo](https://github.com/spring-cloud/spring-cloud-task/tree/master/spring-cloud-task-samples)
* [Spring Cloud Task App Starters Github Repo](https://github.com/spring-cloud-task-app-starters)