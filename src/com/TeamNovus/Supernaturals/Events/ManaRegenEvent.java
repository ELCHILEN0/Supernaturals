package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ManaRegenEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
    private Integer mana;
    
    public ManaRegenEvent(Player player, Integer mana) {
    	super(player);
    	this.mana = mana;
    }
    
	public Integer getMana() {
		return mana;
	}
	
	public void setMana(Integer mana) {
		this.mana = mana;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
