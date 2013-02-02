package com.TeamNovus.Supernaturals.Database;

import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLite extends Driver {
	private String filePath;
	
	public SQLite(String filePath) {
		this.filePath = filePath;
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
	
}
