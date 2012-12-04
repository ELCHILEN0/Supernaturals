package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTriggerEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;

public class CombustEffect {

	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You have been alit with flames!");
		}
	}
	
	@SNEventHandler
	public void onEffectTrigger(EffectTriggerEvent event) {
		event.getEntity().setFireTicks(event.getEffect().getDuration()/event.getEffect().getPeriod());
	}
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You are no longer on fire!");
		}
	}
	
}
