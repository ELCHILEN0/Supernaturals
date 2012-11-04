package com.TeamNovus.SupernaturalRaces.Race.Demon;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class DemonicEvents implements SNEventListener {
	
	@SNEventHandler
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
}
