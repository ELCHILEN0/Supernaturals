package com.TeamNovus.SupernaturalRaces.Models;

import java.util.List;


import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;

public interface SNRace {
	/**
	 * The name of the race
	 */
	String name();
	
	/**
	 * The max power of the race
	 */
	Integer maxPower();
	
	/**
	 * The increment when gaining power
	 */
	Integer powerIncrement();
	
	/**
	 * The spells registered to the race
	 */
	List<SNSpell> spells();
		
	void onPlayerJoinRace(PlayerJoinRaceEvent event);
	void onPlayerLeaveRace(PlayerLeaveRaceEvent event);
		
	void onPlayerDamage(PlayerDamageEvent event);
	void onPlayerDamageEntity(PlayerDamageEntityEvent event);		
}
