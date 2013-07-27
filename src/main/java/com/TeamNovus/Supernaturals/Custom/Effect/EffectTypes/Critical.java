package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Critical extends EffectType {

	public void onEntityDamageEntity(EntityDamageEntityEvent event, Effect effect) {
		if(new Random().nextInt(101) <= effect.getAmplifier()) {
			event.setDamage(event.getDamage() * 2);
			
			if(event.getEntity() instanceof Player) {
				SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
				
				player.sendMessage(ChatColor.GREEN + "Critical hit!");
			}
		}
	}

}
