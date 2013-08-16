package com.TeamNovus.Bounties;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.Bounties.Commands.Commands;
import com.TeamNovus.Bounties.Commands.Common.TestCmd;
import com.TeamNovus.Bounties.Database.StorageManager;
import com.TeamNovus.Bounties.Listeners.PlayerListener;
import com.TeamNovus.Bounties.Tasks.SaveTask;

public class Bounties extends JavaPlugin {
	private static Bounties plugin = null;
	
	public void onEnable() {		
		plugin = this;
				
		// Load Config:
		if(!(new File(getDataFolder(), "config.yml").exists())) {
			saveDefaultConfig();
		}

		// Primary Listeners:
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

		Bukkit.getScheduler().runTaskTimer(this, new SaveTask(), 20 * 60 * 5, 20 * 60 * 5);
		
		// Commands:
		try {
			Commands.register(new TestCmd());
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
//		getCommand("bounties").setExecutor(new BaseCommandExecutor());

		// Load all the data from the database.
		StorageManager.getInstance().loadBounties();	
		StorageManager.getInstance().loadPlayers();			
	}

	public void onDisable() {		
		// Save all the data to the database.
		StorageManager.getInstance().savePlayers();
		StorageManager.getInstance().saveBounties();
		
		// Unscheudle all the tasks.
		Bukkit.getScheduler().cancelTasks(this);
		
		// Nullify any static references.
		plugin = null;
	}

	public static Bounties getPlugin() {
		return plugin;
	}

}
