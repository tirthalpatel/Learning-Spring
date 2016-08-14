# Hystrix Dashboard with Turbine

Demo project for trying a Hystrix Dashboard with Turbine to display the health of circuit breaker in an efficient manner.

Looking at an individual instances Hystrix data is not very useful in terms of the overall health of the system. Turbine is an application that aggregates all of the relevant /hystrix.stream endpoints into a combined /turbine.stream for use in the Hystrix Dashboard.

* Pulling the configuration information from Spring Cloud Config Server and automatically registered with Eureka server.
* Hystrix Dashboard with Turbine 

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server
	- [gs-xxng-service](../gs-xxng-service/README.md) followed by [gs-xxng-client](../gs-xxng-client/README.md): For accessing Hystrix metrics stream of Xing Xong Client app

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Hystrix Dashboard', 'Turbine', 'Turbine Stream', 'Stream Rabbit' and 'Spring Actuator' dependencies.
2. Commented 'Turbine Stream' and 'Stream Rabbit' dependencies in pom.xml, which can be uncommented if there is a need to use [Turbine Stream](http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_turbine_stream).
3. Added property files in 'gs-config-repo' folder: 'gs-hystrix-dashboard.properties'.
4. Added 'bootstrap.properties' in project resources to locate config server.
5. Added '@EnableHystrixDashboard' in 'GsHystrixDashboardApplication.java' to provision Hystrix Dashboard.
6. Added '@EnableTurbine' in 'GsHystrixDashboardApplication.java' and related configuration in 'gs-config-repo/gs-hystrix-dashboard.properties' (specifically 'turbine.appConfig' must be configured to pull metrics from hystrix instances) to get a Turbine server running.
7. Started the application and status could be validated using `http://localhost:9991/admin/info` / `http://localhost:9991/admin/health` url.

### Try

Keep hitting `http://localhost:7005/xxng`, while trying steps (1), (3) and (4). Also, stop Xing Xong service instances and then monitor.

1. Access Xing Xong client's hystrix metrics stream: `http://localhost:7005/admin/hystrix.stream`
2. Open Hystrix Dashboard: `http://localhost:9991/hystrix`
3. Monitor given app's hystrix stream via Hystrix Dashboard, for example, enter Xing Xong client's hystrix stream url and click 'Monitor Stream' button: `http://localhost:7005/admin/hystrix.stream`
4. Monitor aggregated result using Turbine via Hystrix Dashboard, enter turbine stream url and click 'Monitor Stream' button: `http://localhost:9991/turbine.stream`