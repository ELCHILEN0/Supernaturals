package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageByEntityProjectileEvent extends EntityEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled ;
    private Entity damager;
    private double damage;
    private DamageCause cause;
    private Projectile projectile;
    
    public EntityDamageByEntityProjectileEvent(Entity damager, Entity damaged, double damage, DamageCause cause, Projectile projectile) {
		super(damaged);
		this.damager = damager;
		this.damage = damage;
		this.cause = cause;
		this.projectile = projectile;
	}
    
	public Entity getDamaged() {
		return damager;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setDamage(double damage) {
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
