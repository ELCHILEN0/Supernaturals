package com.TeamNovus.Supernaturals.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Driver {
	
	String driver();
	
	void connect() throws ClassNotFoundException, SQLException;
	
	void disconnect() throws SQLException;
	
	Connection getConnection();
	
}
