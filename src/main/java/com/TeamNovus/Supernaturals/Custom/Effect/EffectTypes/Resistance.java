package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Resistance extends EffectType {
	
	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.sendMessage(ChatColor.GREEN + "You take less damage from attacks!");
		}
	}
	
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, Effect effect) {
		event.setDamage(event.getDamage() - (int) Math.floor(effect.getAmplifier() / 2));
	}

	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer(((Player) event.getEntity()));

			player.sendMessage(ChatColor.RED + "You take normal damage from attacks!");				
		}
	}

}
