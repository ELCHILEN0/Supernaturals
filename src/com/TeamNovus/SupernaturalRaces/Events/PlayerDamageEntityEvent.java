package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * Called when a Player damages an entity
 */
public class PlayerDamageEntityEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
	private boolean canceled;
    private Player damager;
    private Entity damagee;
    private DamageCause cause;
    private Integer damage;
    
    public PlayerDamageEntityEvent(Player damager, Entity damagee, DamageCause cause, Integer damage) {
    	this.damager = damager;
    	this.damagee = damagee;
    	this.cause = cause;
    	this.damage = damage;
    }
	
	public Player getPlayer() {
		return damager;
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
		return damagee;
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
		return canceled;
	}

	@Override
	public void setCancelled(boolean canceled) {
		this.canceled = canceled;
	}
}
