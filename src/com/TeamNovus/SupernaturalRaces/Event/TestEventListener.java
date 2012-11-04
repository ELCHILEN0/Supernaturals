package com.TeamNovus.SupernaturalRaces.Event;

import org.bukkit.event.player.PlayerMoveEvent;

public class TestEventListener implements SNEventListener {

	@SNEventHandler()
	public void onPlayerMove(PlayerMoveEvent event) {
		System.out.println("hello from onPlayerMove in TestEventListener!");
	}
	
}
