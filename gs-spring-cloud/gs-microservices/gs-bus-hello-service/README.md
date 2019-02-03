# Demo Hello Microservice to try Spring Cloud Bus

This is plain-vanilla hello microservice to try Spring Cloud Bus (links nodes of a distributed system with a lightweight message broker). Also see [gs-bus-custom-events](../gs-bus-custom-events/README.md). 

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery
* Linking services and service instances together using Spring Cloud Bus (An Event Bus with RabbitMQ or Kafka)

## Pre-requisites

__Dependency__ : RabbitMQ (Alternatively Kafka is also supported by Spring Cloud Bus - just need to switch 'Cloud Bus AMQP' with 'Cloud Bus Kafka' dependency in pom.xml file, if need to use Kafka message broker instead of RabbitMQ)

* [Parent README.md](../README.md)
* Start RabbitMQ server 
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server
	- [gs-bus-bye-service](../gs-bus-bye-service/README.md): Just another plain-vanilla  microservice leveraging Spring Cloud Bus	

## Key Notes

1. Created project using SPRING INITIALIZR. Selected 'Config Client', 'Eureka Discovery', 'Spring Web' and 'Cloud Bus AMQP' dependencies.
2. Added 'gs-bus-custom-events' custom module dependency in pom.xml file.
3. Added property files in 'gs-config-repo' folder: 'gs-bus-hello-service.properties'. Added 'bootstrap.properties' in project resources to locate config server.
4. Created 'HelloController.java' to expose '/hello' service endpoint (just reading message from 'gs-bus-hello-service.properties' file and returning it in response).

### Try

1. Run 'gs-bus-hello-service' and 'gs-bus-bye-service' services (following would also work even when two or more instances of both services are running)

2. Try Hello and Bye services endpoint in browser: `http://localhost:8001/hello` (should display 'Hello, how are you?' message coming from property file config) | `http://localhost:8005/bye` (should display 'See you later, Bye.' message coming from property file config)

3. POST JSON body (e.g. `{"name": "key1", "value": "value1"}`) to '/bus-env' actuator endpoint (updates each instances environment with the specified key/value pair across multiple instance) and check logs of each service instances: `http://localhost:8001/admin/bus-env` --- `{"name": "hello.message", "value": "hello world"}` | `http://localhost:8005/admin/bus-env` --- `{"name": "bye.message", "value": "bye world"}`

4. Try Hello and Bye services endpoint in browser: `http://localhost:8001/hello` | `http://localhost:8005/bye` (both still showing old values and not changed yet b'cas need to refresh)

5. POST to '/bus/refresh' actuator endpoint (reloads each application’s configuration, just as if they had all been pinged on their /refresh endpoint): `http://localhost:8001/admin/bus-refresh` (check logs of each service instances)

6. Try Hello and Bye services endpoint in browser again: `http://localhost:8001/hello` (shows 'hello world' now) | `http://localhost:8005/bye` (shows 'bye world' now)

7. Try trace actuator endpoint to see how instances/nodes are communicating: `http://localhost:8001/admin/trace`

8. POST JSON body (e.g. `{"what": "datetime | date | time"}`) to '/bus-print' actuator endpoint for custom event broadcasting (implemented in 'gs-bus-custom-events' module) and check logs of given 'destination' service instances: `http://localhost:8001/admin/bus-print` --- `{"what": "datetime"}` (by default event broadcasting to all nodes), `http://localhost:8001/admin/bus-print/gs-bus-hello-service:**` --- `{"what": "date"}` (event broadcasting to all 'gs-bus-hello-service' service nodes) | `http://localhost:8005/admin/bus-print/gs-bus-bye-service:**` --- `{"what": "time"}` (event broadcasting to all 'gs-bye-hello-service' service nodes)

9. Try disable Cloud Bus property: configure `spring.cloud.bus.enabled=false` in `gs-config-repo\gs-bus-bye-service.properties` and restart 'gs-bus-bye-service' service. This will disable Cloud Bus for Bye service instances. Try (3) to (8) steps again, which would not broadcast event to Bye service instances anymore.