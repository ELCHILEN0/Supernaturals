package com.TeamNovus.SupernaturalRaces.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL implements Driver {
	private String url;
	
    public MySQL(String host, String port,  String database, String username, String password) throws Exception {        
		url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + (password.equals("") ? "" : "&password=" + password);
		initDriver();
		connect();
    }

	@Override
	public void initDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");		
	}

	@Override
	public void connect() throws SQLException {
    	DriverManager.getConnection(url);		
	}

	@Override
	public void disconnect() throws SQLException {
    	DriverManager.getConnection(url).close();		
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url);
	}

	@Override
	public PreparedStatement prepare(String query) throws SQLException {
		return DriverManager.getConnection(url).prepareStatement(query);
	}

	@Override
	public ResultSet query(PreparedStatement statement) throws SQLException {
		return statement.executeQuery();
	}

	@Override
	public Integer update(PreparedStatement statement) throws SQLException {
		return statement.executeUpdate();
	}

	@Override
	public Boolean execute(PreparedStatement statement) throws SQLException {
		return statement.execute();
	}

}
