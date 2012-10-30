package com.TeamNovus.SupernaturalRaces.Races;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
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
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		
		if(event.getMaterial().equals(Material.BOOK)) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 1));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 1));
			
			for(Entity entity : event.getPlayer().getNearbyEntities(5, 5, 5)) {
				if(entity instanceof Player) {
					Player player = (Player) entity;
					player.setHealth(player.getMaxHealth());
				}
			}
		}
		
		if(event.getMaterial().equals(Material.BOWL)) {
			event.getPlayer().getItemInHand().setType(Material.MUSHROOM_SOUP);
		}
		
		if(event.getMaterial().equals(Material.RED_ROSE)) {
			for(Entity entity : event.getPlayer().getNearbyEntities(5, 5, 5)) {
				if(entity instanceof Player) {
					Player player = (Player) entity;
					player.setHealth(player.getMaxHealth());
				}
			}
			
			Player player = event.getPlayer();
			player.setHealth(player.getMaxHealth());
		}
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		event.setDamage(event.getDamage()/2);
	}

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub

	}
}
