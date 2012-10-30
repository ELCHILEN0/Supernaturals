package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;

public class EntityListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity().getLastDamageCause().getEntity() instanceof Player) {
			PlayerDamageEntityEvent newEvent = new PlayerDamageEntityEvent(
												   (Player) event.getEntity().getLastDamageCause().getEntity(),
												   event.getEntity(),
												   event.getCause(),
												   event.getDamage());
			
			Bukkit.getServer().getPluginManager().callEvent(newEvent);
			
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
		
		if(event.getEntity() instanceof Player) {
			PlayerDamageEntityEvent newEvent = new PlayerDamageEntityEvent(
					   (Player) event.getEntity().getLastDamageCause().getEntity(),
					   event.getEntity(),
					   event.getCause(),
					   event.getDamage());
			
			Bukkit.getServer().getPluginManager().callEvent(newEvent);
			
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}		
		}
	}
	
}
