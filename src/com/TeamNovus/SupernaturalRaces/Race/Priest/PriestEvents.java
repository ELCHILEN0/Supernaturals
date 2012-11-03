package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Metadata.SNAttributeType;
import com.TeamNovus.SupernaturalRaces.Models.SNEvents;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class PriestEvents implements SNEvents {

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		if(event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
			if(player.getAttribute("phaseWalking").type().equals(SNAttributeType.BOOLEAN) && (Boolean) player.getAttribute("phaseWalking").value()) {
				System.out.println(String.valueOf((Boolean) player.getAttribute("phaseWalking").value()));
				if((Math.random()*100) > 45) {
					event.getPlayer().sendMessage(ChatColor.GOLD + "Avoided!");
					event.setCancelled(true);
					return;
				}
			}
		}
		
		event.setDamage(event.getDamage()/5*4);		
		return;
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
