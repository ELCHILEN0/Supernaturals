package com.TeamNovus.SupernaturalRaces;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Commands.ConvertCmd;
import com.TeamNovus.SupernaturalRaces.Commands.HelpCommand;
import com.TeamNovus.SupernaturalRaces.Commands.InfoCmd;
import com.TeamNovus.SupernaturalRaces.Commands.PowerCmd;
import com.TeamNovus.SupernaturalRaces.Commands.RacesCmd;
import com.TeamNovus.SupernaturalRaces.Listeners.CustomListener;
import com.TeamNovus.SupernaturalRaces.Listeners.DefaultEntityListener;
import com.TeamNovus.SupernaturalRaces.Listeners.DefaultWorldListener;
import com.TeamNovus.SupernaturalRaces.Managers.DataManager;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;
import com.TeamNovus.SupernaturalRaces.Managers.RaceManager;
import com.TeamNovus.SupernaturalRaces.Managers.EventManager;
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
		
		getCommand("supernaturals").setExecutor(new HelpCommand());
		getCommand("convert").setExecutor(new ConvertCmd());
		getCommand("races").setExecutor(new RacesCmd());
		getCommand("power").setExecutor(new PowerCmd());
		getCommand("info").setExecutor(new InfoCmd());
						
		getServer().getPluginManager().registerEvents(new CustomListener(), this);
		getServer().getPluginManager().registerEvents(new EventManager(), this);
		getServer().getPluginManager().registerEvents(new DefaultEntityListener(), this);
		getServer().getPluginManager().registerEvents(new DefaultWorldListener(), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new PowerRegenTask(), 20L * 10, 20L * 10);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaveTask(), 20L * 600, 20L * 600);
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
