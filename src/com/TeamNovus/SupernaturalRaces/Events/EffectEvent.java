package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;

public class EffectEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private SNEffect effect;
    
    public EffectEvent(Player player, SNEffect effect) {
    	super(player);
    	this.effect = effect;
    }
    
    public SNEffect getEffect() {
    	return effect;
    }
    
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
