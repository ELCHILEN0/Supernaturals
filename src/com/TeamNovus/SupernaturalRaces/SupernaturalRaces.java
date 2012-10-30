package com.TeamNovus.SupernaturalRaces;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.SupernaturalRaces.Listeners.PlayerListener;
import com.TeamNovus.SupernaturalRaces.Managers.PlayerManager;

public class SupernaturalRaces extends JavaPlugin {
	private PlayerManager playerManager;

	@Override
	public void onEnable() {
		// TODO: Code to execute onEnable
		playerManager = new PlayerManager(this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		playerManager.getPlayer("ELCHILEN0").setRace("ranger");
	}
	
	@Override
	public void onDisable() {
		// TODO: Code to execute onDisable
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
}
