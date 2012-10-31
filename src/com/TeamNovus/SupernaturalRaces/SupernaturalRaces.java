package com.TeamNovus.SupernaturalRaces;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Listeners.EntityListener;
import com.TeamNovus.SupernaturalRaces.Listeners.PlayerListener;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;
import com.TeamNovus.SupernaturalRaces.Managers.RaceManager;

public class SupernaturalRaces extends JavaPlugin {
	private PlayerManager playerManager;
	private RaceManager raceManager;

	@Override
	public void onEnable() {
		playerManager = new PlayerManager(this);
		raceManager = new RaceManager(this);
		
		getServer().getPluginManager().registerEvents(new EntityListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new PowerRegenTask(this), 20L * 20, 20L * 20);
	}
	
	@Override
	public void onDisable() {
		// TODO: Code to execute onDisable
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	
	public RaceManager getRaceManager() {
		return raceManager;
	}
}
