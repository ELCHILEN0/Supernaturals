package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;

public class Weakness extends EffectType {

	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, Effect effect) {
		event.setDamage(event.getDamage() + (int) Math.ceil(effect.getAmplifier() / 2));
	}

}
