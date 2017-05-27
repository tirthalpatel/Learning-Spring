# Eureka Server for Service Discovery

Demo project for Spring Cloud Netflix Eureka server. The server can be configured and deployed to be highly available, with each server replicating state about the registered services to the others.

Refer [Service Registry Pattern](http://microservices.io/patterns/service-registry.html)

## Pre-requisites

* [Parent README.md](../README.md)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information

## Key Notes

1. Created project using SPRING INITIALIZR. Select 'Config Client' and 'Eureka Server' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-discovery-eureka-server.properties' + Peer aware eureka servers profile ('gs-discovery-eureka-server-peer1.properties', 'gs-discovery-eureka-server-peer2.properties')
3. Added 'bootstrap.properties' in project resources to locate config server.
4. Started the application and status could be validated using `http://localhost:8761/admin/info` / `http://localhost:8761/admin/health` url.

### Try

1. Start eureka in standalone mode: `http://localhost:8761/admin/registry`
2. Start two eureka instances, one with 'peer1' profile and another with 'peer2' profile: `http://localhost:8761/admin/registry` / `http://localhost:8762/admin/registry`