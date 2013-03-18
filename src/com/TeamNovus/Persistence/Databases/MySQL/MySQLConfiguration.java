package com.TeamNovus.Persistence.Databases.MySQL;

import com.TeamNovus.Persistence.Databases.Configuration;

public class MySQLConfiguration extends Configuration {

	public MySQLConfiguration() {
		super("mysql");
		setProperty("host", "localhost");
		setProperty("port", "3306");
		setProperty("database", "database");
		setProperty("username", "username");
		setProperty("password", "password");
	}
	
	public MySQLConfiguration setHost(String host) {
		setProperty("host", host);
		
		return this;
	}
	
	public String getHost() {
		return getProperty("host");
	}
	
	public MySQLConfiguration setPort(String port) {
		setProperty("port", port);
		
		return this;
	}
	
	public String getPort() {
		return getProperty("port");
	}
	
	public MySQLConfiguration setDatabase(String database) {
		setProperty("database", database);
		
		return this;
	}
	
	public String getDatabase() {
		return getProperty("database");
	}
	
	public MySQLConfiguration setUsername(String username) {
		setProperty("username", username);
		
		return this;
	}
	
	public String getUsername() {
		return getProperty("username");
	}
	
	public MySQLConfiguration setPassword(String password) {
		setProperty("password", password);
		
		return this;
	}

	public String getPassword() {
		return getProperty("password");
	}
	
}
