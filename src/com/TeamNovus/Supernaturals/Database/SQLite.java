package com.TeamNovus.Supernaturals.Database;


public class SQLite extends Driver {
	
	public SQLite(String filePath) {
		super("jdbc:sqlite:" + filePath, "org.sqlite.JDBC");
	}
	
}
