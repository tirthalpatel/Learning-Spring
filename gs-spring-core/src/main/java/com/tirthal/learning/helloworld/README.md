Hello World - with and without Spring
======================================

Refer "App.java" and "app-container.xml" files in below examples.

[Example 1 - Simple Greeting service to say hello] (https://github.com/tirthalpatel/Learning-Spring/tree/master/gs-spring-core/src/main/java/com/tirthal/learning/helloworld/greeting)

[Example 2 - Output Generator in formats like JSON, XML...] (https://github.com/tirthalpatel/Learning-Spring/tree/master/gs-spring-core/src/main/java/com/tirthal/learning/helloworld/outputgenerator)


What are two types of IOC container in Spring?
---------------------------------------------

* Spring BeanFactory container 
	- simplest container providing basic support for DI and defined by theorg.springframework.beans.factory.BeanFactory interface.

* Spring ApplicationContext container 
	- spring's more advance container which adds more enterprise-specific functionality such as the ability to resolve textual messages from a properties file and the ability to publish application events to interested event listeners. This container is defined by the org.springframework.context.ApplicationContext interface.

* Usage recommendation 
	- The ApplicationContext container includes all functionality of the BeanFactory container, so it is generally recommended over the BeanFactory. BeanFactory can still be used for light weight applications like mobile devices or applet based applications where data volume and speed is significant.
	
* Application context classes in Spring - org.springframework.context.ApplicationContext interface implementations?
	- ClassPathXmlApplicationContext : Loads a context definition from an XML file located in the classpath, treating context definition files as classpath resources.
	- FileSystemXmlApplicationContext : Loads a context definition from an XML file in the file system.
	- XmlWebApplicationContext : Loads context definitions from an XML file contained within a web application.


Bean Scopes in Spring - Total 5 scopes
--------------------------------------

* Valid in any configuration: 
	- Singleton (default bean scope, one instance per Spring container/context)
	- Prototype (A new instance per request)

* Valid in only web-aware spring projects: 
	- Request (return a single bean per HTTP request)
	- Session (return a single bean per HTTP session)
	- Global (return a single bean per Application)

