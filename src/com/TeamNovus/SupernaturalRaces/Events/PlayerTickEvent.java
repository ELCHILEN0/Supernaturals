package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerTickEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    
    public PlayerTickEvent(Player player) {
    	super(player);
    }
    
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
