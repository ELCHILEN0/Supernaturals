package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

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
		plugin.getRaceManager().onPlayerInteractEvent(event);
	}
	
	@EventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		plugin.getRaceManager().onPlayerDamageEvent(event);
	}

	@EventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		plugin.getRaceManager().onPlayerDamageEntityEvent(event);
	}
	
	public void onPlayerDeath(PlayerDeathEvent event) {
		plugin.getRaceManager().onPlayerDeathEvent(event);

	}
	
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		plugin.getRaceManager().onPlayerRespawnEvent(event);

	}
	
}
