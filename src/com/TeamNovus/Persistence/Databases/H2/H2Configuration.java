package com.TeamNovus.Persistence.Databases.H2;

import com.TeamNovus.Persistence.Configuration;

public class H2Configuration extends Configuration {

	public H2Configuration() {
		super("mysql");
		setProperty("path", "h2.db");
	}
	
	public H2Configuration setPath(String path) {
		setProperty("path", path);
		
		return this;
	}
	
	public String getPath() {
		return getProperty("path");
	}

	
}
