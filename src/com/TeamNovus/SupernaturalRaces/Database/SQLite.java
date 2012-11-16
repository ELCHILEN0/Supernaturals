package com.TeamNovus.SupernaturalRaces.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements Driver {
	// Connection variables
	Connection connection;
	// Constructor variables
	private String filePath;
	
	public SQLite(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String driver() {
		return "com.mysql.jdbc.Driver";
	}

	@Override
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
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
