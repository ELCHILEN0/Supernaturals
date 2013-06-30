package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;

public class Hunger extends EffectType {

	public void onFoodLevelChange(FoodLevelChangeEvent event, Effect effect) {
		event.setFoodLevel(event.getFoodLevel() - (int) Math.floor(effect.getAmplifier() / 2));
	}

}
