package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

public class EntityDamageByEntityProjectileEvent extends EntityEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled ;
    private Entity damager;
    private Projectile projectile;
    
    public EntityDamageByEntityProjectileEvent(Entity damagee, Entity damager, Projectile projectile) {
		super(damagee);
		this.damager = damager;
		this.projectile = projectile;
	}
    
	public Entity getDamager() {
		return damager;
	}

	public Projectile getProjectile() {
		return projectile;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
