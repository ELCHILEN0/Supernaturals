package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent event) {
		SupernaturalRaces.getDatabaseManager().loadPlayer(event.getPlayer());
		
		SupernaturalRaces.getEntityManager().addRaceEffects(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		SupernaturalRaces.getEntityManager().addRaceEffects(event.getPlayer());
	}
		
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		SupernaturalRaces.getDatabaseManager().unloadPlayer(event.getPlayer());
	}
	
}
