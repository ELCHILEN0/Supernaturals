package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerEvent;

/**
 * Called when a player is damaged.
 */
public class PlayerDamageEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
    private DamageCause cause;
    private Integer damage;
    
    public PlayerDamageEvent(Player player, DamageCause cause, Integer damage) {
    	super(player);
    	this.cause = cause;
    	this.damage = damage;
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