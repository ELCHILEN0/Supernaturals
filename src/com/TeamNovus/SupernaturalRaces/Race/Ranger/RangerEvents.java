package com.TeamNovus.SupernaturalRaces.Race.Ranger;

import org.bukkit.entity.LivingEntity;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityByProjectileEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class RangerEvents implements SNEventListener {
	
	@SNEventHandler
	public void onPlayerDamageEntityByProjectileEvent(PlayerDamageEntityByProjectileEvent event) {
		System.out.println(((LivingEntity) event.getEntity()).getHealth());
		event.setDamage(event.getDamage() * 2);
	}

}
