package com.TeamNovus.SupernaturalRaces;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Commands.BaseCommandExecutor;
import com.TeamNovus.SupernaturalRaces.Commands.DefaultCommands;
import com.TeamNovus.SupernaturalRaces.Database.Database;
import com.TeamNovus.SupernaturalRaces.Listeners.CustomListener;
import com.TeamNovus.SupernaturalRaces.Listeners.DefaultEntityListener;
import com.TeamNovus.SupernaturalRaces.Listeners.DefaultServerListener;
import com.TeamNovus.SupernaturalRaces.Managers.CommandManager;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;
import com.TeamNovus.SupernaturalRaces.Managers.RaceManager;
import com.TeamNovus.SupernaturalRaces.Managers.EventManager;
import com.TeamNovus.SupernaturalRaces.Tasks.PowerRegenTask;
import com.TeamNovus.SupernaturalRaces.Tasks.SaveTask;

public class SupernaturalRaces extends JavaPlugin {
	private static SupernaturalRaces plugin;
	private static CommandManager commandManager;
	private static PlayerManager playerManager;
	private static RaceManager raceManager;
	private static Database database;

	@Override
	public void onEnable() {
		plugin = this;
		
		reloadConfiguration();

		commandManager = new CommandManager();
		playerManager = new PlayerManager();
		raceManager = new RaceManager();
		database = new Database();
		
		getCommand("supernatural").setExecutor(new BaseCommandExecutor());
		
		database.connect();
		database.setup();
		database.load();
		
		commandManager.registerClass(DefaultCommands.class);
						
		getServer().getPluginManager().registerEvents(new CustomListener(), this);
		getServer().getPluginManager().registerEvents(new EventManager(), this);
		getServer().getPluginManager().registerEvents(new DefaultEntityListener(), this);
		getServer().getPluginManager().registerEvents(new DefaultServerListener(), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new PowerRegenTask(), 20L * 10, 20L * 10);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaveTask(), 20L * 60, 20L * 60);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				new DefaultServerListener().onServerTick();
			}
		}, 1, 1);
	}
	
	@Override
	public void onDisable() {
		// Cancel all tasks
		getServer().getScheduler().cancelTasks(this);
		
		// Save to database and close the connection
		database.save();
		database.close();
		
		// Set all static references to null
		plugin = null;
		database = null;
		commandManager = null;
		playerManager = null;
		raceManager = null;
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
	
	public static Database getDb() {
		return database;
	}

	public static CommandManager getCommandManager() {
		return commandManager;
	}
}
