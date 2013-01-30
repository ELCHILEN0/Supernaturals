package com.TeamNovus.Supernaturals;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.Supernaturals.Commands.BaseCommandExecutor;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Commands.Common.AdminCommands;
import com.TeamNovus.Supernaturals.Commands.Common.DefaultCommands;
import com.TeamNovus.Supernaturals.Commands.Common.PluginCommands;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Listeners.EntityListener;
import com.TeamNovus.Supernaturals.Listeners.PlayerListener;
import com.TeamNovus.Supernaturals.Listeners.SupernaturalListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.ExperienceListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.HealthListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.HungerListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.SpeedListener;

public class Supernaturals extends JavaPlugin {
	private static Supernaturals plugin = null;

	@Override
	public void onEnable() {		
		plugin = this;
		
		Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new SupernaturalListener(), this);
	
		// Custom Field Listeners:
		Bukkit.getPluginManager().registerEvents(new HealthListener(), this);
		Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
		Bukkit.getPluginManager().registerEvents(new SpeedListener(), this);
		
		Bukkit.getPluginManager().registerEvents(new ExperienceListener(), this);
		
		// Tick all Entities:
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			
			@Override
			public void run() {
				for(SNEntity e : SNEntities.i.getEntites()) {
					e.tick();
				}				
			}
		}, 1, 1);
		
		getCommand("supernaturals").setExecutor(new BaseCommandExecutor());
		
		CommandManager.registerClass(DefaultCommands.class);
		CommandManager.registerClass(PluginCommands.class);
		CommandManager.registerClass(AdminCommands.class);
	}

	
	@Override
	public void onDisable() {
		
	}
	
	public static Supernaturals getPlugin() {
		return plugin;
	}
	
}
