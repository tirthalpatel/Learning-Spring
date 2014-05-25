Java based spring configuration with almost no XML
---------------------------------------------------
	
* Introduced in Spring 3.0 for configuring a Spring application with almost no XML (No need of applicationContext.xml)

* <context:component-scan> automatically registers beans that are annotated with certain stereotype annotations. But it also automatically loads in Java-based configuration classes that are annotated with @Configuration.

* @Configure: java class level annotation, which replaces the applicationContext xml files and works as a configuration scanner of Spring.

* @Bean: a method level annotation, which is used to get instance of Spring Beans + use of setter injection or construction injection.

