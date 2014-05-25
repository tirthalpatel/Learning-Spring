package com.tirthal.learning.misc.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceInjection {

	/**
	 * In case a bean needs a reference to resource loader to load a resource dynamically, it can be autowired like
	 * this. The spring injects the default resourceloader of the current application context into this. You must be
	 * thinking that we can also inject the application Context object also since its also a resourceloader. Well you
	 * can, but we should not because you will unnecessary inject the complete Application context object. So u can
	 * simply inject the handy resource loader object to carry the resource operations
	 */
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * In case you want to inject resources that are static, u can directly inject that. See the resourceContext.xml. We
	 * have just set the path and using the bean property element and spring will automatically resolve it to a resource
	 * object
	 */
	private Resource textFile;

	public void setTextFile(Resource textFile) {
		this.textFile = textFile;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public Resource getTextFile() {
		return textFile;
	}

	public Resource loadDynamicResource() {
		// In case when resource needs to be loaded based on some condition - Add logic
		boolean someCondition = true;
		if (someCondition) {
			return resourceLoader.getResource("com/tirthal/learning/misc/io/sample2.txt");
		} else {
			return resourceLoader.getResource("com/tirthal/learning/misc/io/sample1.txt");
		}

	}

}
