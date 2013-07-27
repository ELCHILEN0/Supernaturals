package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;

public class Confusion extends EffectType {

	public void onEffectTick(EntityEffectTickEvent event, Effect effect) {		
		if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, effect.getAmplifier()));
		}
	}

}
