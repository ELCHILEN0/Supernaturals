package com.TeamNovus.Supernaturals.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Driver {
	protected String url;
	protected String driver;
	private Connection connection;
		
	public Driver(String url, String driver) {
		this.url = url;
		this.driver = driver;
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url);
	}
		
	public void disconnect() throws SQLException {
		connection.close();
	}
	
	public Connection getConnection() {
		return connection;
	}
}
