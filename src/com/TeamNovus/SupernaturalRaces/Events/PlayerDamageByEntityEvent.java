package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerEvent;

public class PlayerDamageByEntityEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
    private DamageCause cause;
	private Entity entity;
    private Integer damage;
    
    public PlayerDamageByEntityEvent(Player player, Entity entity, DamageCause cause, Integer damage) {
    	super(player);
    	this.entity = entity;
    	this.cause = cause;
    	this.damage = damage;
    }
    
    public Entity getEntity() {
    	return entity;
    }

	public DamageCause getCause() {
		return cause;
	}

	public Integer getDamage() {
		return damage;
	}
	
	public void setDamage(Integer damage) {
		this.damage = damage;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
