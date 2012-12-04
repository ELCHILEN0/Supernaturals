package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.entity.LivingEntity;

import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTriggerEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Util.EffectUtil;

public class HealEffect {
	
	@SNEventHandler
	public void onEffectTick(EffectTickEvent event) {
		if(event.getEntity() instanceof LivingEntity) {
			EffectUtil.addPotionGraphicalEffect((LivingEntity) event.getEntity(), 0xFF0000, 40);
		}
	}
	
	@SNEventHandler
	public void onEffectTrigger(EffectTriggerEvent event) {
		if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity)event.getEntity()).setHealth(event.getEffect().getAmplifier());
		}
	}
	
}
