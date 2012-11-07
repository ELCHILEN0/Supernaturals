package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Listeners.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Listeners.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class PriestEvents implements SNEventListener {
	
	@SNEventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		if(event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
			if(player.isPhaseWalking()) {
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
}
