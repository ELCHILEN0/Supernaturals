package com.TeamNovus.SupernaturalRaces.Race.Human;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Events.EffectTriggerEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class BleedEffect implements SNEventListener {

	@SNEventHandler
	public void onEffectTrigger(EffectTriggerEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "Bleeding...");
		}
		
		if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity) event.getEntity()).damage(1);
		}
	}
	
}
