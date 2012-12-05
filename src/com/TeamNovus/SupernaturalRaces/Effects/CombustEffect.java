package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class CombustEffect implements SNEventListener {

	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You have been ignited!");
			event.getEntity().setFireTicks(event.getEffect().getDuration());
		}
	}
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			event.getEntity().setFireTicks(0);
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You are no longer on fire!");
		}
	}
	
}
