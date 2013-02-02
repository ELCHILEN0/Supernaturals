package com.TeamNovus.Supernaturals.Database;

import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQL extends Driver {
	String host;
	String port;
	String username;
	String password;
	String database;
	
	public MySQL(String host, String port, String username, String password, String database) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.database = database;		
	}

	@Override
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
	}

	@Override
	public void disconnect() throws SQLException {
		connection.close();
	}
}
