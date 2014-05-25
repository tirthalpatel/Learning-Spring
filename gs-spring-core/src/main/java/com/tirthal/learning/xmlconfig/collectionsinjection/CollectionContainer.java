package com.tirthal.learning.xmlconfig.collectionsinjection;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * POJO containing collection types of instance variables
 * 
 * @author tirthalp
 * 
 */
@SuppressWarnings("rawtypes")
public class CollectionContainer {

	private List list;

	private Set set;

	private Map map;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
