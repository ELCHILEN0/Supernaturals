package com.TeamNovus.Supernaturals.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;

public abstract class EntityEffectEvent extends EntityEvent {
	protected Effect effect;
	
	public EntityEffectEvent(Entity entity, Effect effect) {
		super(entity);
		
		this.effect = effect;
	}
	
	public Effect getEffect() {
		return effect;
	}
	
	public EffectType getEffectType() {
		return effect.getType();
	}	
}