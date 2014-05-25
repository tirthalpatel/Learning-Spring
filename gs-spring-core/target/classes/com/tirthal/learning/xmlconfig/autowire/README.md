Automatic beans wiring
----------------------

* Helps reduce or even eliminate the need for <property> and <constructor-arg> elements by letting Spring automatically figure out how to wire bean dependencies.

* Using "autowire" attribute in <bean> element. For example - <bean id="xyz" class="com.abc.XYZ" autowire="byName/byType/constructor/autodetect">
	- byName : Attempts to match all properties of the autowired bean with beans that have the same name (or ID) as the properties. Properties for which there’s no matching bean will remain unwired.
	- byType : Attempts to match all properties of the autowired bean with beans whose types are assignable to the properties. Properties for which there’s no matching bean will remain unwired. To overcome ambiguities with autowiring by type available two options are - primary="false" & autowire-candidate="false".
	- constructor : Tries to match up a constructor of the autowired bean with beans whose types are assignable to the constructor arguments.
	- autodetect : Attempts to apply constructor autowiring first. If that fails, byType will be tried.
	
* Using "default-autowire" attribute in the root <beans> element - it is possible to still override the default on a bean-by-bean basis using the autowire attribute.