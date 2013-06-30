package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;

public class Invisibility extends EffectType {

	public void onEffectTick(EntityEffectTickEvent event, Effect effect) {
		if(event.getEntity() instanceof Player) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				player.hidePlayer((Player) event.getEntity());
			}
		} else if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, effect.getAmplifier()));
		}
	}
	
	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				player.showPlayer((Player) event.getEntity());
			}
		}
	}

}
