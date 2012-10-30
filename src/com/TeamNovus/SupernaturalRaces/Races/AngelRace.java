package com.TeamNovus.SupernaturalRaces.Races;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
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
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		
		if(event.getMaterial().equals(Material.FEATHER)) {
			if(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
				Vector apply = new Vector(0, 1, 0);
				apply.multiply(1);
				event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(apply));
			}
		}
		
		if(event.getMaterial().equals(Material.SUGAR)) {
			for(Entity entity : event.getPlayer().getNearbyEntities(5, 5, 5)) {
				if(entity instanceof Player) {
					Player player = (Player) entity;
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 3));
				}
			}
		}
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		Player player = event.getPlayer();
		
		if(event.getCause().equals(DamageCause.FALL)) {
			event.setCancelled(true);
		}
		
		if(player.getWorld().getTime() < 12000) {
			event.setDamage(event.getDamage()/2);
		}
		
		if(player.getWorld().getTime() > 12000) {
			event.setDamage(event.getDamage()*2);
		}
	}

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}

}
