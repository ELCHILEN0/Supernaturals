package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;

public class Slowness extends EffectType {
	
	public void onEffectBegin(EntityEffectBeginEvent event, Effect effect) {
		if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effect.getDuration(), effect.getAmplifier()));
		}
	}
	
}
