package com.TeamNovus.SupernaturalRaces.Models;

import org.bukkit.event.Event;

public interface Race {
	String name();
	String tag();
	
	void onPlayerJoinRace(Event event);
	void onPlayerLeaveRace(Event event);
}
