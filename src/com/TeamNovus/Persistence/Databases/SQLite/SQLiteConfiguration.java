package com.TeamNovus.Persistence.Databases.SQLite;

import com.TeamNovus.Persistence.Databases.Configuration;

public class SQLiteConfiguration extends Configuration {

	public SQLiteConfiguration() {
		super("sqlite");
		setProperty("file", "data.db");
	}
	
	public SQLiteConfiguration setFilePath(String filePath) {
		setProperty("file", filePath);
		
		return this;
	}
	
	public String getFilePath() {
		return getProperty("file");
	}
}
