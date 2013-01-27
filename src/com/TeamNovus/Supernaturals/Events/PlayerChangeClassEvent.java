package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class PlayerChangeClassEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
    private SNClass from;
    private SNClass to;
    
    public PlayerChangeClassEvent(Player player, SNClass from, SNClass to) {
    	super(player);
    	this.from = from;
    	this.to = to;
    }
    
    /**
     * The SNClass that the player is converting from.
     * 
     * @return The players last SNClass.
     */
	public SNClass getFrom() {
		return from;
	}
	
	/**
	 * The SNClass that the player is converting into.
	 * 
	 * @return The players new SNClass.
	 */
	public SNClass getTo() {
		return to;
	}
	
	/**
	 * Set the SNClass that the player is converting into.
	 * 
	 * @param to - The players new SNClass.
	 */
	public void setTo(SNClass to) {
		this.to = to;
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