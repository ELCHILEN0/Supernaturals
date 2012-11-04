package com.TeamNovus.SupernaturalRaces.Race.Demon;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEvents;

public class DemonicEvents implements SNEvents {

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		Player player = event.getPlayer();
		
		if(event.getCause().equals(DamageCause.FIRE_TICK) || event.getCause().equals(DamageCause.FIRE) || event.getCause().equals(DamageCause.LAVA)) {
			player.setFireTicks(0);
			event.setCancelled(true);
		}
		
		if(event.getCause().equals(DamageCause.DROWNING)) {
			event.setDamage(event.getDamage()*3);
			return;
		}
		
		if(player.getLocation().getBlock().isLiquid()) {
			event.setDamage(event.getDamage()*2);
			return;
		}		
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
	public void onPlayerMove(PlayerMoveEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {
		// TODO Auto-generated method stub
		
	}

}
