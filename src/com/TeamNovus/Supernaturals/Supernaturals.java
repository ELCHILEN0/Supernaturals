package com.TeamNovus.Supernaturals;

import java.io.File;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.Supernaturals.Commands.BaseCommandExecutor;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Commands.Common.AdminCommands;
import com.TeamNovus.Supernaturals.Commands.Common.DefaultCommands;
import com.TeamNovus.Supernaturals.Commands.Common.PluginCommands;
import com.TeamNovus.Supernaturals.Database.StorageManager;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Listeners.EntityListener;
import com.TeamNovus.Supernaturals.Listeners.PlayerListener;
import com.TeamNovus.Supernaturals.Listeners.SupernaturalListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.ExperienceListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.HealthListener;
import com.TeamNovus.Supernaturals.Listeners.Custom.HungerListener;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Statistics.Cooldown;

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
		Bukkit.getPluginManager().registerEvents(new HealthListener(), this);
		Bukkit.getPluginManager().registerEvents(new HungerListener(), this);
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

		// Regain Mana:
		getServer().getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				for(SNPlayer p : SNPlayers.i.getOnlinePlayers()) {
					p.setMana(p.getMana() + 10, true);
				}
			}
		}, 10 * 20, 10 * 20);

		// Save Data:
		getServer().getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				StorageManager.getInstance().savePlayers();
			}
		}, 60 * 20, 60 * 20);
		
		// Lower Cooldowns:
		getServer().getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				for(SNPlayer p : SNPlayers.i.getOnlinePlayers()) {
					Iterator<Cooldown> iterator = p.getCooldowns().iterator();
					while(iterator.hasNext()) {
						Cooldown cooldown = iterator.next();
						
						cooldown.setRemainingTicks(cooldown.getRemainingTicks() - 1);
						
						if(cooldown.getRemainingTicks() <= 0) {
							iterator.remove();
							
							// TODO: Power Refresh Event
						}
					}
				}
			}
		}, 60 * 20, 60 * 20);

		getCommand("supernaturals").setExecutor(new BaseCommandExecutor());

		CommandManager.registerClass(DefaultCommands.class);
		CommandManager.registerClass(PluginCommands.class);
		CommandManager.registerClass(AdminCommands.class);

		// Load all the data from the database.
		StorageManager.getInstance().loadPlayers();			
		StorageManager.getInstance().loadEntities();			
	}


	@Override
	public void onDisable() {

		// Save all the data to the database.
		StorageManager.getInstance().savePlayers();
		StorageManager.getInstance().saveEntities();
	}

	public static Supernaturals getPlugin() {
		return plugin;
	}

}
