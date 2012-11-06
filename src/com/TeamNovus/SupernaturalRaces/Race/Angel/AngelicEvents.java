package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Listeners.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Listeners.SNEventListener;

public class AngelicEvents implements SNEventListener {
	@SNEventHandler
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
}
