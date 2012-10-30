package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;

public class EntityListener implements Listener {
	private SupernaturalRaces plugin;

	public EntityListener(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			PlayerDamageEvent newEvent = new PlayerDamageEvent(
										   (Player) event.getEntity(),
										   event.getCause(),
										   event.getDamage());
			
			plugin.getServer().getPluginManager().callEvent(newEvent);
			
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
			
			plugin.getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());
						
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
	}
	
}
