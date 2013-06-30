package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectTypeListener;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;

public class Confusion extends EffectType {

	@EffectTypeListener(eventClass = EntityEffectTriggerEvent.class)
	public void onEffectTrigger(EntityEffectTriggerEvent event, Effect effect) {		
		Location location = event.getEntity().getLocation();
		location.setPitch(90 - new Random(181).nextInt());

		event.getEntity().teleport(location);
		
		if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, effect.getAmplifier()));
		}
	}

}
