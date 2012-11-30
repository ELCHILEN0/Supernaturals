package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerDamageEntityByProjectileEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
    private Entity damaged;
    private Integer damage;
    private Projectile projectile;

    public PlayerDamageEntityByProjectileEvent(Player player, Entity damaged, Integer damage, Projectile projectile) {
    	super(player);
    	this.damaged = damaged;
    	this.damage = damage;
    	this.projectile = projectile;
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
	
	public Projectile getProjectile() {
		return projectile;
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
