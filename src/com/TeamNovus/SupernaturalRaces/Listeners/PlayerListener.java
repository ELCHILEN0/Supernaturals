package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
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
	public void onPlayerInteract(PlayerInteractEvent event) {
		SNPlayer player = plugin.getPlayerManager().getPlayer(event.getPlayer());
		plugin.getPlayerManager().getRace(player).onPlayerInteract(event);
	}
	
}
