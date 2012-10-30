package com.TeamNovus.SupernaturalRaces.Races;

import net.minecraft.server.EntityLiving;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.Race;

public class PriestRace implements Race {

	@Override
	public String name() {
		return "Priest";
	}
	
	
	@Override
	public String tag() {
		return "Priest";
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
		if(event.getMaterial().equals(Material.FIREBALL)) {
			for (Entity entity : event.getPlayer().getWorld().getEntities()) {
					if (entity.getLocation() == event.getPlayer().getEyeLocation()) {
						event.getPlayer().sendMessage("test");
						if(entity instanceof EntityLiving) {
							entity.setFireTicks(20 * 5);
						}
					}
				}		
			}
	}

	@Override
	public void onPlayerDamage(EntityDamageEvent event) {
		// TODO Auto-generated method stub
		
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
