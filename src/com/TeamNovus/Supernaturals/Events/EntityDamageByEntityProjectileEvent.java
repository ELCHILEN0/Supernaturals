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
    private Integer damage;
    private DamageCause cause;
    private Projectile projectile;
    
    public EntityDamageByEntityProjectileEvent(Entity damager, Entity damaged, Integer damage, DamageCause cause, Projectile projectile) {
		super(damaged);
		this.damager = damager;
		this.damage = damage;
		this.cause = cause;
		this.projectile = projectile;
	}
    
	public Entity getDamaged() {
		return damager;
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
