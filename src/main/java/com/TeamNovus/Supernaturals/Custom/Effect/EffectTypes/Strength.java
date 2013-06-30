package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;

public class Strength extends EffectType {

	public void onEntityDamageEntity(EntityDamageEntityEvent event, Effect effect) {
		event.setDamage(event.getDamage() + (int) Math.ceil(effect.getAmplifier() / 2));
	}

}
