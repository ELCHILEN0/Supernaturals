package com.TeamNovus.Supernaturals.Database;


public class MySQL extends Driver {
	
	public MySQL(String host, String port, String username, String password, String database) {
		super("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password" + password, "com.mysql.jdbc.Driver");
	}
	
}
