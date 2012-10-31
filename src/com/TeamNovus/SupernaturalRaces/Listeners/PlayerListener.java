package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;

public class PlayerListener implements Listener {
	private SupernaturalRaces plugin;
	
	public PlayerListener(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {	
		// TODO: Execute code when players 
	}
	
	@EventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		// TODO: Execute code when players are damaged
	}

	@EventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO: Execute code when players damage
	}
	
}
