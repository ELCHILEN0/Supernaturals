package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageEntityEvent extends EntityEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private Entity damaged;
    private double damage;
    private DamageCause cause;
    
    public EntityDamageEntityEvent(Entity damager, Entity damaged, double d, DamageCause cause) {
		super(damager);
		this.damaged = damaged;
		this.damage = d;
		this.cause = cause;
	}
    
	public Entity getDamaged() {
		return damaged;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public void setDamage(double d) {
		this.damage = d;
	}
	
	public DamageCause getCause() {
		return cause;
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
