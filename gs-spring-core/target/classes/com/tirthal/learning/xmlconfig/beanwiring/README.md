Declaring beans
----------------
* Injecting through constructor - <constructor-arg  value=""  ref="" />
* Creating beans using factory method - <bean factory-method="" … />
* Bean scoping - <bean scope="" … />
	- singleton - Scopes the bean definition to a single instance per Spring container / Application context (default).
	- prototype - Allows a bean to be instantiated any number of times (once per use).
	- request - Scopes a bean definition to an HTTP request. Only valid when used with a web-capable Spring context (such as with Spring MVC).
	- session - Scopes a bean definition to an HTTP session. Only valid when used with a web-capable Spring context (such as with Spring MVC).
	- global - session Scopes a bean definition to a global HTTP session. Only valid when used in a portlet context.
* Initializing and destroying beans - <bean init-method="" destroy-method="" … />
* Bean definition inheritance - <bean  id="" class=""  parent="">

Injecting into bean properties
------------------------------
* Injecting simple values - <property name="" value="" />
* Referencing other beans  - <property name="" ref="" />
* Wiring properties with Spring's p namespace - using "P:xxx"
* Wiring collections
	- <list> - wiring a list of values, allowing duplicates
	- <set> - wiring a set of values, ensuring no duplicates
	- <map> - wiring a collection of name-value pairs where name and value can be of any type
	- <props> - wiring a collection of name-value pairs where the name and value are both Strings
* Wiring null - <property name="someNonNullProperty"><null/></property>