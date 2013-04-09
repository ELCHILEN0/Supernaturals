package com.TeamNovus.Supernaturals;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.Supernaturals.Commands.BaseCommandExecutor;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Commands.Common.AdminCommands;
import com.TeamNovus.Supernaturals.Commands.Common.DefaultCommands;
import com.TeamNovus.Supernaturals.Commands.Common.PluginCommands;
import com.TeamNovus.Supernaturals.Database.StorageManager;
import com.TeamNovus.Supernaturals.Listeners.EntityListener;
import com.TeamNovus.Supernaturals.Listeners.PlayerListener;
import com.TeamNovus.Supernaturals.Listeners.SupernaturalListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.ExperienceListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.HungerListener;
import com.TeamNovus.Supernaturals.Tasks.CooldownTask;
import com.TeamNovus.Supernaturals.Tasks.EntityTickTask;
import com.TeamNovus.Supernaturals.Tasks.ManaRegainTask;
import com.TeamNovus.Supernaturals.Tasks.SaveTask;

public class Supernaturals extends JavaPlugin {
	private static Supernaturals plugin = null;
	
	@Override
	public void onEnable() {		
		plugin = this;
				
		// Load Config:
		if(!(new File(getDataFolder(), "config.yml").exists())) {
			saveDefaultConfig();
		}
		
		// Primary Listeners:
		Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new SupernaturalListener(), this);

		// Custom Field Listeners:
		Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
		Bukkit.getPluginManager().registerEvents(new ExperienceListener(), this);

		// Schedule Tasks:
		Bukkit.getScheduler().runTaskTimer(this, new EntityTickTask(), 1, 1);
		Bukkit.getScheduler().runTaskTimer(this, new CooldownTask(), 1, 1);
		Bukkit.getScheduler().runTaskTimer(this, new ManaRegainTask(), 20 * 10, 20 * 10);
		Bukkit.getScheduler().runTaskTimer(this, new SaveTask(), 20 * 10, 20 * 10);

		// Commands:
		getCommand("supernaturals").setExecutor(new BaseCommandExecutor());

		CommandManager.register(DefaultCommands.class);
		CommandManager.register(PluginCommands.class);
		CommandManager.register(AdminCommands.class);

		// Load all the data from the database.
		StorageManager.getInstance().loadPlayers();			
		StorageManager.getInstance().loadEntities();			
	}

	@Override
	public void onDisable() {

		// Save all the data to the database.
		StorageManager.getInstance().savePlayers();
		StorageManager.getInstance().saveEntities();
		
		plugin = null;
	}

	public static Supernaturals getPlugin() {
		return plugin;
	}

}
