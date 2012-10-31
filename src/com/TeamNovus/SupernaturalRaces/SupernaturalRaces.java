package com.TeamNovus.SupernaturalRaces;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Listeners.EntityListener;
import com.TeamNovus.SupernaturalRaces.Listeners.PlayerListener;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;
import com.TeamNovus.SupernaturalRaces.Managers.RaceManager;
import com.TeamNovus.SupernaturalRaces.Util.Database;

public class SupernaturalRaces extends JavaPlugin {
	private PlayerManager playerManager;
	private RaceManager raceManager;
	private Database database;

	@Override
	public void onEnable() {
		playerManager = new PlayerManager(this);
		raceManager = new RaceManager(this);
		
		connect();
		
		playerManager.load();
		
		getServer().getPluginManager().registerEvents(new EntityListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new PowerRegenTask(this), 20L * 20, 20L * 20);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaveTask(this), 20L * 300, 20L * 300);
	}
	
	@Override
	public void onDisable() {
		// TODO: Code to execute onDisable
	}
	
	public void connect() {
		if(getConfig().getString("storage.type").equalsIgnoreCase("mysql")) {
			database = new Database(this, "localhost", "test", "root", "root");
		} else {
			database = new Database(this, getDataFolder() + File.separator + "data.dat");
		}
		
		System.out.println("Checking tables for any modifications...");
		database.query("CREATE  TABLE IF NOT EXISTS `sn_players` (" +
				"  `name` INT NOT NULL ," +
				"  `race` TEXT NULL ," +
				"  `power` INT NOT NULL DEFAULT 0 ," +
				"  PRIMARY KEY (`name`) ,"  +
				"  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )" +
				"ENGINE = InnoDB");
		System.out.println("SQL loaded sucessfully!");
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public RaceManager getRaceManager() {
		return raceManager;
	}
	
	public Database getDb() {
		return database;
	}
}
