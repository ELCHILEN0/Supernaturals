package com.TeamNovus.Supernaturals.Listeners;

import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityByProjectileEvent;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;

public class EntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Projectile) {
			// Called when an entity damages another entity with a projectile.
			EntityDamageEntityByProjectileEvent entityDamageEntityByProjectile = new EntityDamageEntityByProjectileEvent(((Projectile) event.getDamager()).getShooter(), event.getEntity(), event.getDamage(), event.getCause(), (Projectile) event.getDamager());
			
			event.setDamage(entityDamageEntityByProjectile.getDamage());
			
			if (entityDamageEntityByProjectile.isCancelled())
				event.setCancelled(true);
			
			// Called when an entity is damaged by a projectile.
			EntityDamageEntityByProjectileEvent entityDamageByEntityProjectile = new EntityDamageEntityByProjectileEvent(((Projectile) event.getDamager()).getShooter(), event.getEntity(), event.getDamage(), event.getCause(), (Projectile) event.getDamager());
			
			event.setDamage(entityDamageByEntityProjectile.getDamage());
			
			if (entityDamageByEntityProjectile.isCancelled())
				event.setCancelled(true);
		}		
		
		// Called when an entity damages another entity.
		EntityDamageEntityEvent entityDamageEntity = new EntityDamageEntityEvent(event.getDamager(), event.getEntity(), event.getDamage(), event.getCause());
		
		event.setDamage(entityDamageEntity.getDamage());
		
		if (entityDamageEntity.isCancelled())
			event.setCancelled(true);
	}
	
}
