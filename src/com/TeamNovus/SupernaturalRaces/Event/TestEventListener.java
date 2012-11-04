package com.TeamNovus.SupernaturalRaces.Event;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class TestEventListener implements SNEventListener {

	// This event should be invoked FROM the EventManager
	@SNEventHandler()
	public void onPlayerMove(PlayerMoveEvent event) {
	}
	
	
	@SNEventHandler()
	public void onPlayerDeath(PlayerDeathEvent event) {
		System.out.println("player has died!");
	}
}
