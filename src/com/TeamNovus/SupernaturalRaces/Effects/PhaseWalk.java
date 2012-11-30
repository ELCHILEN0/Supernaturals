package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageByEntityEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;

public class PhaseWalk {

	@SNEventHandler
	public void onEffectBeginEvent(EffectBeginEvent event) {
		event.getPlayer().sendMessage(ChatColor.GOLD + "You begin to dematerialize...");
	}
	
	@SNEventHandler
	public void onPlayerDamageByEntity(PlayerDamageByEntityEvent event) {
		if(event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
			if((Math.random() * 100) > 45) {
				event.getPlayer().sendMessage(ChatColor.GOLD + "Avoided!");
				event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 0);
				event.setCancelled(true);
				return;
			}
		}
	}

	@SNEventHandler
	public void onEffectExpireEvent(EffectExpireEvent event) {
		event.getPlayer().sendMessage(ChatColor.GOLD + "You return to your body!");
	}
}
