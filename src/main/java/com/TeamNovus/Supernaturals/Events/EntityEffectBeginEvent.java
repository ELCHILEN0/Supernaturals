package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;

public class EntityEffectBeginEvent extends EntityEffectEvent {
	private static final HandlerList handlers = new HandlerList();
    
    public EntityEffectBeginEvent(Entity entity, Effect effect) {
		super(entity, effect);
	}
 
    public HandlerList getHandlers() {
        return handlers;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
