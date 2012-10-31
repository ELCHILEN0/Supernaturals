package com.TeamNovus.SupernaturalRaces.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Driver {
	void initDriver() throws ClassNotFoundException;
	
    void connect() throws SQLException;
        
    void disconnect() throws SQLException;
        
    Connection getConnection() throws SQLException;
    
    PreparedStatement prepare(String query) throws SQLException;
    
    ResultSet query(PreparedStatement statement) throws SQLException;

    Integer update(PreparedStatement statement) throws SQLException;
    
    Boolean execute(PreparedStatement statement) throws SQLException;
}
