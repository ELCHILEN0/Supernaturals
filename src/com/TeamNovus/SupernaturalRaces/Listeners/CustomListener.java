package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageByEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;

/**
 * This class is designed to listen to and trigger any custom listeners
 *
 */
public class CustomListener implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			PlayerDamageEvent newEvent = new PlayerDamageEvent(
										   (Player) event.getEntity(),
										   event.getCause(),
										   event.getDamage());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);
			
			event.setDamage(newEvent.getDamage());
			
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}		
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {	
		if(event.getDamager() instanceof Player) {
			PlayerDamageEntityEvent newEvent = new PlayerDamageEntityEvent(
												   (Player) event.getDamager(),
												   event.getEntity(),
												   event.getCause(),
												   event.getDamage());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());
						
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
		
		if(event.getEntity() instanceof Player) {
			PlayerDamageByEntityEvent newEvent = new PlayerDamageByEntityEvent(
												   (Player) event.getEntity(),
												   event.getEntity(),
												   event.getCause(),
												   event.getDamage());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());
						
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		SupernaturalRaces.getRaceManager().onPlayerInteractEvent(event);
	}
	
}
