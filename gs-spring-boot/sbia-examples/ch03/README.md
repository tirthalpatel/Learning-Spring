## Configurations Customization in Spring Boot

* Added "spring-boot-starter-security" dependency for security configuration, which provides basic security configuration by default. 
* For example, by default, when opening the application in browser, immediately showing an HTTP Basic authentication dialog box (user name = user / password = check in log as 'Using default security password')
* See "SecurityConfig.java" for custom security configuration sample
* For example of customizing Spring boot provided application error pages, see "error.html"
* See "application.yml" for externalizing configuration with properties
* For Spring Boot default configuration customization in application.properties / application.yml, refer http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
* Environment specific configuration using Profiles (VM Arguments : "-Dspring.profiles.active=<tell-which-profile-to-use>“)