package com.TeamNovus.SupernaturalRaces.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements Driver {
	// Connection variables
	Connection connection;
	// Constructor variables
	private String host;
	private String port;
	private String username;
	private String password;
	private String database;
	
	public MySQL(String host, String port, String username, String password, String database) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.database = database;
	}
	
	@Override
	public String driver() {
		return "com.mysql.jdbc.Driver";
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

	@Override
	public Connection getConnection() {
		return connection;
	}

}
