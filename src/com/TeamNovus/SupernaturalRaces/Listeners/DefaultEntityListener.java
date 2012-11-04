package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;

/**
 * This class is used for overriding behiavor that is not dependent on a player or a race.
 * Any listener can be pointed here and it is best to use attributes, metadata etc.
 */
public class DefaultEntityListener implements Listener {
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent event) {
		
	}
	
	@EventHandler
	public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
		
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		
	}
	
	@EventHandler
	public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
		
	}
	
	@EventHandler
	public void EntityPortalEnterEvent(EntityPortalEnterEvent event) {
		
	}
	
	@EventHandler
	public void EntityRegainHealthEvent(EntityRegainHealthEvent event) {
		
	}
	
	@EventHandler
	public void EntityShootBowEvent(EntityShootBowEvent event) {
		
	}
	
	@EventHandler
	public void EntityTameEvent(EntityTameEvent event) {
		
	}
	
	@EventHandler
	public void EntityTeleportEvent(EntityTeleportEvent event) {
		
	}
	
}
