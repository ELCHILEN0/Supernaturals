package com.TeamNovus.SupernaturalRaces.Races;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.Race;

public class AngelRace implements Race {

	@Override
	public String name() {
		return "Angel";
	}

	@Override
	public String tag() {
		return "Angel";
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
		if(event.getMaterial().equals(Material.FEATHER)) {
			if(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
				Vector apply = new Vector(0, 1, 0);
				apply.multiply(1);
				event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(apply));
			}
		}		
	}

	@Override
	public void onPlayerDamage(EntityDamageEvent event) {
		if(event.getCause().equals(DamageCause.FALL)) {
			event.setCancelled(true);
		}
		
		if(event.getCause().equals(DamageCause.FIRE)) {
			event.setDamage(event.getDamage() + 1);
		}
		
		if(event.getCause().equals(DamageCause.POISON)) {
			event.setDamage(event.getDamage() + 1);
		}
	}

	@Override
	public void onProjectileHitEvent(ProjectileHitEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInventoryClick(InventoryClickEvent event) {
		// TODO Auto-generated method stub
		
	}

}
