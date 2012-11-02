package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {	
		SupernaturalRaces.getRaceManager().onPlayerInteractEvent(event);
	}
	
	@EventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		SupernaturalRaces.getRaceManager().onPlayerDamageEvent(event);
	}

	@EventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		SupernaturalRaces.getRaceManager().onPlayerDamageEntityEvent(event);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		SupernaturalRaces.getRaceManager().onPlayerDeathEvent(event);
	}
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		SupernaturalRaces.getRaceManager().onPlayerRespawnEvent(event);
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		SupernaturalRaces.getRaceManager().onPlayerMoveEvent(event);
	}
	
}
