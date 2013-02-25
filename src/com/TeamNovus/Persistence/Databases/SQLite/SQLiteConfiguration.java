package com.TeamNovus.Persistence.Databases.SQLite;

import com.TeamNovus.Persistence.Configuration;

public class SQLiteConfiguration extends Configuration {

	public SQLiteConfiguration() {
		super("mysql");
		setProperty("path", "sqlite.db");
	}
	
	public SQLiteConfiguration setPath(String path) {
		setProperty("path", path);
		
		return this;
	}
	
	public String getPath() {
		return getProperty("path");
	}

	
}
