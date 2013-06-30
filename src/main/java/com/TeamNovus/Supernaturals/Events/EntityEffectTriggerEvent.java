package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;

public class EntityEffectTriggerEvent extends EntityEvent {
	private static final HandlerList handlers = new HandlerList();
    private Effect effect;
    
    public EntityEffectTriggerEvent(Entity entity, Effect effect) {
		super(entity);
		this.effect = effect;
	}

    public Effect getEffect() {
		return effect;
	}
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
