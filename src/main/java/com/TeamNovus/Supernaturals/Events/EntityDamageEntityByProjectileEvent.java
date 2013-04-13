package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageEntityByProjectileEvent extends EntityEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled ;
    private Entity damaged;
    private Integer damage;
    private DamageCause cause;
    private Projectile projectile;
    
    public EntityDamageEntityByProjectileEvent(Entity damager, Entity damaged, Integer damage, DamageCause cause, Projectile projectile) {
		super(damager);
		this.damaged = damaged;
		this.damage = damage;
		this.cause = cause;
		this.projectile = projectile;
	}
    
	public Entity getDamaged() {
		return damaged;
	}
	
	public Integer getDamage() {
		return damage;
	}
	
	public void setDamage(Integer damage) {
		this.damage = damage;
	}
	
	public DamageCause getCause() {
		return cause;
	}
	
	public Projectile getProjectile() {
		return projectile;
	}

	public boolean isCancelled() {
		return cancelled;
	}

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