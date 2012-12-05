package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTriggerEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Util.EffectUtil;

public class HealEffect implements SNEventListener {
	
	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You begin to heal quicker!");
			event.getEntity().setFireTicks(event.getEffect().getDuration());
		}
	}
	
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
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "Your healing returns to normal.");
		}
	}
	
}
