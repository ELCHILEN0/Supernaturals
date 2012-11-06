package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Listeners.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Listeners.SNEventListener;

public class WerewolfEvents implements SNEventListener {

	@SNEventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		if(event.getPlayer().getWorld().getTime() > 12000) {
			event.setDamage(event.getDamage()*2);
		}		
	}

	@SNEventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		if(event.getPlayer().getWorld().getTime() > 12000) {
			event.setDamage(event.getDamage()*2);
		}
	}
}
