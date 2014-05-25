Spring Expression Language (SpEL for short)
------------------------------------------- 
* It is a powerful expression language that supports querying and manipulating an object graph at runtime. SpEL expressions can be used with XML or annotation based configuration metadata for defining Bean definitions. In both cases the syntax to define the expression is of the form #{ <expression string> }.

* SpEL expressions are ultimately just Strings that are tricky to test and have no IDE support for syntax checking. So fight the temptation to put too much logic into a SpEL expression.


SpEL syntax (notes taken from Spring in Action book)
---------------------------------------------------
* SpEL (Spring Expression Language) fundamentals
	* wiring values into a bean’s properties or constructor arguments using expressions that are evaluated at runtime.
	* Referencing beans, properties, methods - <property name="song" value="#{songSelector.selectSong()?.toUpperCase()}"/> [? = null safe check]
	* Working with Types - T() operator - gives us access to static methods and constants on a given class - <property name="multiplier" value="#{T(java.lang.Math).PI}"/>

* Performing operations on SpEL values
	* Arithmetic +, -, *, /, %, ^
	* Relational <, >, ==, <=, >=, lt, gt, eq, le, ge
	* Logical and, or, not, |
	* Conditional ?: (ternary), ?: (Elvis)
	* Regular expression matches
	* Examples,
		<property name="remainder" value="#{counter.total % counter.count}"/>
		<property name="fullName" value="#{performer.firstName + ' ' + performer.lastName}"/>
		<property name="hasCapacity" value="#{counter.total le 100000}"/>
		<property name="largeCircle" value="#{shape.kind =='circle' and shape.perimeter gt 10000}"/>
		<property name="outOfStock" value="#{!product.available}"/>
		<property name="inrument" value="#{songSelector.selectSong()=='JingleBells'?piano:saxophone}"/>
		<property name="validEmail" value="#{admin.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com'}"/>

* Exploring collections in SpEL
	<util:list id="cities">
		<bean class="com.habuma.spel.cities.City" p:name="Atlanta" p:state="GA" p:population="537958"/>
		<bean class="com.habuma.spel.cities.City" p:name="Dallas" p:state="TX" p:population="1279910"/>
	</util:list>
	<property name="chosenCity" value="#{cities[2]}"/>
	<property name="chosenCity" value="#{cities['Dallas']}"/>
	
	<util:properties id="settings" location="classpath:settings.properties"/>
	<property name="accessToken" value="#{settings['twitter.accessToken']}"/>
	
	<property name="homePath"value="#{systemEnvironment['HOME']}"/> -  systemEnvironment contains all of the environment variables on the machine running the application
	
	<property name="homePath" value="#{systemProperties['application.home']}"/> -  contains all of the properties that were set in Java as the application started (typically using the -D argument)
	
	<property name="bigCities" value="#{cities.?[population gt 100000]}"/> - a selection operator  (.?[])
	<property name="aFirstBigCity" value="#{cities.^[population gt 100000]}"/> - operator for selecting the first matching item (.^[])
	<property name="aLastBigCity" value="#{cities.$[population gt 100000]}"/> - operator for selecting the last matching item (.$[])
	<property name="cityNames" value="#{cities.![name+','+state]}"/>  - projecting operator for collecting a particular property from each of the members of a collection into a new collection (.![])
	<property name="cityNames" value="#{cities.?[population gt 100000].![name+','+state]}"/> - collection selection and projection
