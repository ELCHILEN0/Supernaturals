package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class SpeedListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		// Reduce usage by first checking if the players speed needs to be changed.
		if(player.getPlayer().getWalkSpeed() != player.getSpeed()) {
			player.updateSpeed();
		}
	}
	
}
