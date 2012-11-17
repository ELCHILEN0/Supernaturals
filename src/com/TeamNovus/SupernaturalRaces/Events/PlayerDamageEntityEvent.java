package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerEvent;

/**
 * Called when a Player damages an entity
 */
public class PlayerDamageEntityEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
    private Entity damaged;
    private DamageCause cause;
    private Integer damage;
    
    public PlayerDamageEntityEvent(Player player, Entity damaged, DamageCause cause, Integer damage) {
    	super(player);
    	this.damaged = damaged;
    	this.cause = cause;
    	this.damage = damage;
    }

	public DamageCause getDamageCause() {
		return cause;
	}

	public Integer getDamage() {
		return damage;
	}
	
	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	public Entity getEntity() {
		return damaged;
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
