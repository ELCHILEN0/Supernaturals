package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Metabolism extends EffectType {

	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.sendMessage(ChatColor.GREEN + "Your hunger now depletes slower!");
		}
	}
	
	public void onFoodLevelChange(FoodLevelChangeEvent event, Effect effect) {
		event.setFoodLevel(event.getFoodLevel() + (int) Math.ceil(effect.getAmplifier() / 2));
	}


	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer(((Player) event.getEntity()));

			player.sendMessage(ChatColor.RED + "Your hunger now depletes normally!");				
		}
	}
	
}
