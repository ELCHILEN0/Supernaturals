package com.TeamNovus.SupernaturalRaces;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Commands.ConvertCmd;
import com.TeamNovus.SupernaturalRaces.Commands.InfoCmd;
import com.TeamNovus.SupernaturalRaces.Commands.PowerCmd;
import com.TeamNovus.SupernaturalRaces.Commands.RacesCmd;
import com.TeamNovus.SupernaturalRaces.Event.SNEventManager;
import com.TeamNovus.SupernaturalRaces.Listeners.EntityListener;
import com.TeamNovus.SupernaturalRaces.Listeners.PlayerListener;
import com.TeamNovus.SupernaturalRaces.Managers.DataManager;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;
import com.TeamNovus.SupernaturalRaces.Managers.RaceManager;
import com.TeamNovus.SupernaturalRaces.Tasks.PowerRegenTask;
import com.TeamNovus.SupernaturalRaces.Tasks.SaveTask;

public class SupernaturalRaces extends JavaPlugin {
	private static SupernaturalRaces plugin;
	private static DataManager database;
	private static PlayerManager playerManager;
	private static RaceManager raceManager;

	@Override
	public void onEnable() {
		plugin = this;
		
		reloadConfiguration();
		
		playerManager = new PlayerManager();
		raceManager = new RaceManager();
		
		getCommand("convert").setExecutor(new ConvertCmd());
		getCommand("races").setExecutor(new RacesCmd());
		getCommand("power").setExecutor(new PowerCmd());
		getCommand("info").setExecutor(new InfoCmd());
						
		getServer().getPluginManager().registerEvents(new EntityListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new SNEventManager(), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new PowerRegenTask(), 20L * 10, 20L * 10);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaveTask(this), 20L * 300, 20L * 300);
	}
	
	@Override
	public void onDisable() {
		// Set all static references to null
		plugin = null;
		database = null;
		playerManager = null;
		raceManager = null;
		
		// Cancel all tasks
		getServer().getScheduler().cancelTasks(this);		
	}
	
	public void reloadConfiguration() {
		if(!(new File(getDataFolder() + File.separator + "config.yml").exists())) {
			saveDefaultConfig();
		}
		reloadConfig();
	}

	public static SupernaturalRaces getPlugin() {
		return plugin;
	}
	
	public static PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public static RaceManager getRaceManager() {
		return raceManager;
	}
	
	public static DataManager getDb() {
		return database;
	}
}
