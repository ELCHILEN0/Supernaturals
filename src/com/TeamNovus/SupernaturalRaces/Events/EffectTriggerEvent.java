package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;

public class EffectTriggerEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private SNEffect effect;
    
    public EffectTriggerEvent(Entity entity, SNEffect effect) {
    	super(entity);
    	this.effect = effect;
    }
    
	public SNEffect getEffect() {
		return effect;
	}

	public void setEffect(SNEffect effect) {
		this.effect = effect;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
