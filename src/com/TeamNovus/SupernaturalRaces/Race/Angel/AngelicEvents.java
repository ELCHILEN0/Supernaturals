package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class AngelicEvents implements SNEventListener {
	@SNEventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		Player player = event.getPlayer();
				
		if(event.getCause().equals(DamageCause.FALL)) {
			event.setCancelled(true);
			return;
		}
		
		if(player.getWorld().getTime() < 12000) {
			event.setDamage((int) (event.getDamage()*.75));
			return;
		}
		
		if(player.getWorld().getTime() > 12000) {
			event.setDamage((int) (event.getDamage() * 1.25));
			return;
		}			
	}
}
