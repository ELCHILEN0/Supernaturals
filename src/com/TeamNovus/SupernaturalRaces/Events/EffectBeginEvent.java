package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;

public class EffectBeginEvent extends PlayerEvent {
	private static final HandlerList handlers = new HandlerList();
    
    public EffectBeginEvent(Player player, SNEffect effect) {
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
