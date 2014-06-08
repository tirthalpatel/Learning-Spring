Spring MVC - learning made easy
=================================

Sample code snippet for getting started (gs) with Spring MVC.

Skip steps of creating new project, instead use this project directly for quick start
--------------------------------------------------------------------------------------
* Prerequisites for this project setup
	- Java 7
	- Maven
	- Spring Tool Suite (STS) / Eclipse (preferably with Spring plugins installed)
	- VMware vFabric tc Server / Tomcat
* Just import project in STS and switch to Spring perspective. Fix build path errors, such as JRE configuration. 
* Configure new Server in STS (VMWare vFabric tc Server / Tomcat) and add "gs-spring-mvc" web app in server. Start server and confirm there are no errors in log. That's it.

* Now you should see "Hello World" message in browser, when you open this url - http://localhost:8080/gs-spring-mvc/greeting.html


Steps of building "Hello World" Spring MVC Project 
--------------------------------------------------
* Step-1: Created new Maven project. Added dependency in pom.xml - spring-webmvc, servlet-api, jstl.
* Step-2: Updated "web.xml" file to add Spring DispatcherServlet configuration.
* Step-3: Created new "servlet-context.xml" file for Spring MVC configuration (Note: the relative path of this file must match as specified in "contextConfigLocation" param of "web.xml").
* Step-4: Added Controller - "HelloController.java"
* Step-5: Added view - "hello.jsp" 