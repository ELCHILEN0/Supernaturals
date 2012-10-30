package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class PlayerListener implements Listener {
	private SupernaturalRaces plugin;
	
	public PlayerListener(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerMove(event);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getEntity());
		plugin.getPlayerManager().getRace(player).onPlayerDeath(event);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerRespawn(event);
	}
	
	@EventHandler
	public void onPlayerVelocity(PlayerVelocityEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerVelocity(event);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerTeleport(event);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerInteract(event);
	}
	
	@EventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerDamage(event);
	}

	@EventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerDamageEntity(event);
	}
	
}
