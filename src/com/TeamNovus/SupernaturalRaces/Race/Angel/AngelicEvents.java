package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEvents;

public class AngelicEvents implements SNEvents {
	
	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		Player player = event.getPlayer();
				
		if(event.getCause().equals(DamageCause.FALL)) {
			event.setCancelled(true);
			return;
		}
		
		if(player.getWorld().getTime() < 12000) {
			event.setDamage(event.getDamage()/2);
			return;
		}
		
		if(player.getWorld().getTime() > 12000) {
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