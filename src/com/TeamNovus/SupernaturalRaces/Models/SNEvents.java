package com.TeamNovus.SupernaturalRaces.Models;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;

/**
 * A Generic representation of the events that can be registered to a class
 * Multiple SNEvents can be added to specific races.  Each event will be called
 * in the order that it is added to the events.
 */
public interface SNEvents {

	void onPlayerDamageEntity(PlayerDamageEntityEvent event);
	
	void onPlayerDamage(PlayerDamageEvent event);
	
	void onPlayerDeath(PlayerDeathEvent event);
	
	void onPlayerRespawn(PlayerRespawnEvent event);
	
	void onPlayerMove(PlayerMoveEvent event);
	
	void onPlayerJoinRace(PlayerJoinRaceEvent event);
	
	void onPlayerLeaveRace(PlayerLeaveRaceEvent event);
	
}
