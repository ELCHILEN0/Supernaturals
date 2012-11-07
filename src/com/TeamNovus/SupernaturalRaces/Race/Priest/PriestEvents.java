package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class PriestEvents implements SNEventListener {
	
	@SNEventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		if(event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
			if(player.getRemainingPhaseWalk() > 0) {
				if((Math.random()*100) > 45) {
					event.getPlayer().sendMessage(ChatColor.GOLD + "Avoided!");
					event.setCancelled(true);
					return;
				}
			}
		}
		
		event.setDamage(event.getDamage()*(9/10));		
		return;
	}
}
