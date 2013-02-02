package com.TeamNovus.Supernaturals.Database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Driver {	
	protected Connection connection;
	
	public abstract void connect() throws ClassNotFoundException, SQLException;
		
	public abstract void disconnect() throws SQLException;
	
	public Connection getConnection() {
		return connection;
	}
}
