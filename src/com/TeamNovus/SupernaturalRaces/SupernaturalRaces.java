package com.TeamNovus.SupernaturalRaces;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Listeners.EntityListener;
import com.TeamNovus.SupernaturalRaces.Listeners.PlayerListener;
import com.TeamNovus.SupernaturalRaces.Managers.DataManager;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;
import com.TeamNovus.SupernaturalRaces.Managers.RaceManager;

public class SupernaturalRaces extends JavaPlugin {
	private DataManager database;
	private PlayerManager playerManager;
	private RaceManager raceManager;

	@Override
	public void onEnable() {
		reloadConfiguration();
		
		playerManager = new PlayerManager(this);
		raceManager = new RaceManager(this);
						
		getServer().getPluginManager().registerEvents(new EntityListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new PowerRegenTask(this), 20L * 20, 20L * 20);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaveTask(this), 20L * 300, 20L * 300);
	}
	
	@Override
	public void onDisable() {
		// TODO: Code to execute onDisable
	}
	
	public void reloadConfiguration() {
		if(!(new File(getDataFolder() + File.separator + "config.yml").exists())) {
			saveDefaultConfig();
		}
		reloadConfig();
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public RaceManager getRaceManager() {
		return raceManager;
	}
	
	public DataManager getDb() {
		return database;
	}
}
