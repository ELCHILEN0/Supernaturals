package com.TeamNovus.SupernaturalRaces.Models;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

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
	
	void onPlayerJoinRace(Event event);
	void onPlayerLeaveRace(Event event);
	
	void onPlayerMove(Event event);
	void onPlayerDeath(PlayerDeathEvent event);
	void onPlayerRespawn(PlayerRespawnEvent event);
	void onPlayerVelocity(PlayerVelocityEvent event);
	void onPlayerTeleport(PlayerTeleportEvent event);
	void onPlayerInteract(PlayerInteractEvent event);
	
	void onPlayerDamage(EntityDamageEvent event);
	
	void onProjectileHitEvent(ProjectileHitEvent event);
	
	void onInventoryClick(InventoryClickEvent event);
}
