package com.TeamNovus.Supernaturals.Database;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.TeamNovus.Persistence.Databases.Database;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLConfiguration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLDatabase;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteConfiguration;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteDatabase;
import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class StorageManager {
	private static StorageManager instance = null;
	private Database database;
	
	public static StorageManager getInstance() {
		if(instance == null)
			instance = new StorageManager();

		return instance;
	}

	public StorageManager() {		
		FileConfiguration config = Supernaturals.getPlugin().getConfig();
		
		if(config.getString("storage.type").equalsIgnoreCase("mysql")) {
			MySQLConfiguration configuration = new MySQLConfiguration();
			
			configuration.setHost(config.getString("storage.mysql.host"));
			configuration.setPort(config.getString("storage.mysql.port"));
			configuration.setUsername(config.getString("storage.mysql.username"));
			configuration.setPassword(config.getString("storage.mysql.password"));
			configuration.setDatabase(config.getString("storage.mysql.database"));
			
			database = new MySQLDatabase(configuration);
		} else if(config.getString("storage.type").equalsIgnoreCase("sqlite")) {
			SQLiteConfiguration configuration = new SQLiteConfiguration();
			
			configuration.setFilePath(Supernaturals.getPlugin().getDataFolder() + config.getString("storage.sqlite.file-path"));
			
			database = new SQLiteDatabase(configuration);
		}
		
		if(database == null) {
			// Fail
			System.out.println("Unable to connect!");
		}
		
	}

	public void loadPlayers() {
		List<SNPlayer> players = database.findAll(SNPlayer.class);

		for(SNPlayer p : players) {
			SNPlayers.i.attach(p);
		}
	}

	public void savePlayers() {	
		database.saveAll(SNPlayers.i.getAllPlayers());
	}

	public void loadEntities() {
		List<SNEntity> players = database.findAll(SNEntity.class);
		
		for(SNEntity e : players) {
			SNEntities.i.attach(e);
		}
	}

	public void saveEntities() {	
		database.saveAll(SNEntities.i.getEntites());

	}
}
