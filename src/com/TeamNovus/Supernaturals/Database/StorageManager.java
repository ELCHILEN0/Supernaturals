package com.TeamNovus.Supernaturals.Database;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.TeamNovus.Persistence.Database;
import com.TeamNovus.Persistence.Databases.H2.H2Configuration;
import com.TeamNovus.Persistence.Databases.H2.H2Database;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLConfiguration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLDatabase;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteConfiguration;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteDatabase;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class StorageManager {
	private Database database;
	private static StorageManager instance = null;

	public static StorageManager getInstance() {
		if(instance == null)
			instance = new StorageManager();

		return instance;
	}

	public StorageManager() {
		FileConfiguration cfg = Supernaturals.getPlugin().getConfig();
		
		if(cfg.getString("database.type").equalsIgnoreCase("mysql")) {
			MySQLConfiguration configuration = new MySQLConfiguration();
			
			// Load the MySQL details:
			configuration.setHost(cfg.getString("database.mysql.host"));
			configuration.setPort(cfg.getString("database.mysql.port"));
			configuration.setDatabase(cfg.getString("database.mysql.database"));
			configuration.setUsername(cfg.getString("database.mysql.username"));
			configuration.setPassword(cfg.getString("database.mysql.password"));
			
			database = new MySQLDatabase(configuration);
		} else if(cfg.getString("database.type").equalsIgnoreCase("sqlite")) {
			SQLiteConfiguration configuration = new SQLiteConfiguration();
			
			// Load the SQLite details:
			configuration.setPath(Supernaturals.getPlugin().getDataFolder() + File.separator + cfg.getString("database.sqlite.path"));
			
			database = new SQLiteDatabase(configuration);
		} else if(cfg.getString("database.type").equalsIgnoreCase("h2")) {
			H2Configuration configuration = new H2Configuration();
			
			// Load the H2 details:
			configuration.setPath(cfg.getString("database.h2.database"));
			
			database = new H2Database(configuration);
		}

		if(database == null) {
			// Fail
			return;
		}

		try {
			database.connect();
			
			database.setCheckTableOnRegistration(true);

			database.registerTable(SNPlayer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadPlayers() {
		List<SNPlayer> players = database.findAll(SNPlayer.class);
		
		for(SNPlayer p : players) {
			SNPlayers.i.attach(p);
		}
	}

	public void savePlayers() {	
		for(SNPlayer p : SNPlayers.i.getAllPlayers()) {
			database.save(SNPlayer.class, p);
		}
	}
}
