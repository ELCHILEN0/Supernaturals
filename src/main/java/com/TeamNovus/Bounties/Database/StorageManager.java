package com.TeamNovus.Bounties.Database;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.TeamNovus.Bounties.BBounties;
import com.TeamNovus.Bounties.BPlayers;
import com.TeamNovus.Bounties.Bounties;
import com.TeamNovus.Bounties.Player.BBounty;
import com.TeamNovus.Bounties.Player.BPlayer;
import com.TeamNovus.Persistence.Databases.Database;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLConfiguration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLDatabase;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteConfiguration;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteDatabase;

public class StorageManager {
	private static StorageManager instance = null;
	private Database database;
	
	public static StorageManager getInstance() {
		if(instance == null)
			instance = new StorageManager();

		return instance;
	}

	public StorageManager() {		
		FileConfiguration config = Bounties.getPlugin().getConfig();
		
		if(config.getString("database.type").equalsIgnoreCase("mysql")) {
			MySQLConfiguration configuration = new MySQLConfiguration();
			
			configuration.setHost(config.getString("database.mysql.host"));
			configuration.setPort(config.getString("database.mysql.port"));
			configuration.setUsername(config.getString("database.mysql.username"));
			configuration.setPassword(config.getString("database.mysql.password"));
			configuration.setDatabase(config.getString("database.mysql.database"));
			
			database = new MySQLDatabase(configuration);
		} else if(config.getString("database.type").equalsIgnoreCase("sqlite")) {
			SQLiteConfiguration configuration = new SQLiteConfiguration();
			
			configuration.setFilePath(Bounties.getPlugin().getDataFolder() + File.separator + config.getString("database.sqlite.file"));
			
			database = new SQLiteDatabase(configuration);
		}
		
		if(database == null) {
			// Fail
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getLogger().severe("Unable to connect to the database!");
			Bukkit.getLogger().severe("Verify that your information is correct then try again!");
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getServer().getPluginManager().disablePlugin(Bounties.getPlugin());
			return;
		} else {
			Bukkit.getLogger().info("-----------------------------------------------------");
			Bukkit.getLogger().info("Database connection sucessful!");
			Bukkit.getLogger().info("-----------------------------------------------------");
		}
		
		database.connect();
	}

	public void loadPlayers() {
		List<BPlayer> players = database.findAll(BPlayer.class);

		for(BPlayer p : players) {
			BPlayers.i.attach(p);
		}
	}

	public void savePlayers() {	
		Bukkit.getScheduler().runTaskAsynchronously(Bounties.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				database.saveAll(BPlayers.i.getAllPlayers());				
			}
		});
	}

	public void loadBounties() {
		List<BBounty> bounties = database.findAll(BBounty.class);

		for(BBounty b : bounties) {
			BBounties.i.attach(b);
		}
	}

	public void saveBounties() {	
		Bukkit.getScheduler().runTaskAsynchronously(Bounties.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				database.saveAll(BBounties.i.getBBounties());				
			}
		});
	}
}
