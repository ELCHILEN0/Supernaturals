package com.TeamNovus.Supernaturals.Database;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.TeamNovus.Persistence.Databases.Database;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLConfiguration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLDatabase;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteConfiguration;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteDatabase;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Supernaturals;
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
			
			configuration.setFilePath(Supernaturals.getPlugin().getDataFolder() + File.separator + config.getString("storage.sqlite.file"));
			
			database = new SQLiteDatabase(configuration);
		}
		
		if(database == null) {
			// Fail
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getLogger().severe("Unable to connect to the database!");
			Bukkit.getLogger().severe("Verify that your information is correct then try again!");
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getServer().getPluginManager().disablePlugin(Supernaturals.getPlugin());
			return;
		}
		
		database.connect();
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
		Bukkit.getLogger().info("Entity loading and saving has not yet been implemented!");
		
//		List<SNEntity> players = database.findAll(SNEntity.class);
//		
//		for(SNEntity e : players) {
//			SNEntities.i.attach(e);
//		}
	}

	public void saveEntities() {	
		Bukkit.getLogger().info("Entity loading and saving has not yet been implemented!");
		
//		database.saveAll(SNEntities.i.getEntites());

	}
}
