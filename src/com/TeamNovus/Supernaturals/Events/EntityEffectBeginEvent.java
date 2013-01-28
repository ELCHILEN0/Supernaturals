package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

import com.TeamNovus.Supernaturals.Entity.Effects.Effect;

public class EntityEffectBeginEvent extends EntityEvent {
	private static final HandlerList handlers = new HandlerList();
    private Effect effect;
    
    public EntityEffectBeginEvent(Entity entity, Effect effect) {
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
