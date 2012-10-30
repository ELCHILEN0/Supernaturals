package com.TeamNovus.SupernaturalRaces.Models;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;

public interface Race {
	/**
	 * The name of the race
	 * Must be unique
	 */
	String name();
	/**
	 * The tag of the race for use with chat systems
	 * @return
	 */
	String tag();
	
	void onPlayerJoinRace(PlayerJoinRaceEvent event);
	void onPlayerLeaveRace(PlayerLeaveRaceEvent event);
	
	void onPlayerMove(PlayerMoveEvent event);
	void onPlayerDeath(PlayerDeathEvent event);
	void onPlayerRespawn(PlayerRespawnEvent event);
	void onPlayerVelocity(PlayerVelocityEvent event);
	void onPlayerTeleport(PlayerTeleportEvent event);
	void onPlayerInteract(PlayerInteractEvent event);
	
	void onPlayerDamage(PlayerDamageEvent event);
	void onPlayerDamageEntity(PlayerDamageEntityEvent event);		
}
