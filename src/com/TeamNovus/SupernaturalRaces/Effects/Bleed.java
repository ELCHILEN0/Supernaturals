package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;

public class Bleed {
	@SNEventHandler
	public void onEffectBeginEvent(EffectBeginEvent event) {
		event.getPlayer().sendMessage(ChatColor.RED + "You are now bleeding...");
	}

	@SNEventHandler
	public void onEffectEvent(EffectEvent event) {
		event.getPlayer().damage(2);
		event.getPlayer().sendMessage(ChatColor.RED + "Bleeding...");
	}
	
	@SNEventHandler
	public void onEffectExpireEvent(EffectExpireEvent event) {
		event.getPlayer().sendMessage(ChatColor.GREEN + "You are no longer bleeding!");
	}
}
