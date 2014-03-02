package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;


import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Util.ParticleEffectUtils;

public class Barrage extends EffectType {
	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer(((Player) event.getEntity()));
		
			player.sendMessage(ChatColor.GREEN + "You can now barrage your enemies!");				
		}
	}
	
	public void onEntityDamageEntity(EntityDamageEntityEvent event, Effect effect) {
		if(new Random().nextInt(101) <= effect.getAmplifier() / 2) {
			event.setDamage(event.getDamage() * effect.getAmplifier());
			
			if(event.getEntity() instanceof Player) {		
				SNPlayer player = SNPlayer.getPlayer(((Player) event.getEntity()));
				
				player.sendMessage(ChatColor.GREEN + "**Barraged**");
			}
			
			ParticleEffectUtils.fireworkParticleShower(event.getDamaged().getLocation(), Color.RED);
		}
	}

	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer(((Player) event.getEntity()));

			player.sendMessage(ChatColor.RED + "You can no barrage your enemies.");				
		}
	}
}
