package com.TeamNovus.SupernaturalRaces.Races;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.Race;

public class DemonRace implements Race {

	@Override
	public String name() {
		return "Demon";
	}

	@Override
	public String tag() {
		return "Demon";
	}

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDeath(PlayerDeathEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerVelocity(PlayerVelocityEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerInteract(PlayerInteractEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		System.out.println(event.getCause().toString());
		if(event.getCause().equals(DamageCause.LAVA) || event.getCause().equals(DamageCause.FIRE) || event.getCause().equals(DamageCause.FIRE_TICK)) {
			event.setCancelled(true);
		}
		
		if(event.getCause().equals(DamageCause.DROWNING)) {
			event.setDamage(event.getDamage() * 3);
		}
		
		if(event.getPlayer().getLocation().getBlock().isLiquid()) {
			event.setDamage(event.getDamage() * 2);
		}
	}

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}

}
