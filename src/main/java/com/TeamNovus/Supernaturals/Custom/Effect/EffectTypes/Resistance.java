package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;

public class Resistance extends EffectType {

	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, Effect effect) {
		event.setDamage(event.getDamage() - (int) Math.floor(effect.getAmplifier() / 2));
	}

}
