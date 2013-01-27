package com.TeamNovus.Supernaturals.Listeners;

import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;

public class EntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Projectile) {
	
		}		
		
		EntityDamageEntityEvent newEvent = new EntityDamageEntityEvent(event.getDamager(), event.getEntity(), event.getDamage(), event.getCause());
		
		event.setDamage(newEvent.getDamage());
		
		if (newEvent.isCancelled())
			event.setCancelled(true);
	}
	
}
