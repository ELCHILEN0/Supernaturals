package com.TeamNovus.Persistence.Databases;

import java.util.HashMap;

public abstract class Configuration {
	private String name;
	private HashMap<String, String> properties = new HashMap<String, String>();
	
	public Configuration(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Configuration setProperties(HashMap<String, String> properties) {
		this.properties = properties;
		
		return this;
	}
	
	public HashMap<String, String> getProperties() {
		return properties;
	}
	
	public Configuration setProperty(String property, String value) {
		properties.put(property, value);
		
		return this;
	}
	
	public String getProperty(String property) {
		return properties.get(property);
	}
	
	public Configuration removeProperty(String property) {
		properties.remove(property);
		
		return this;
	}
	
	public boolean hasProperty(String property) {
		return properties.containsKey(property);
	}
}
