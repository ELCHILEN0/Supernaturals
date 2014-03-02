package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Util.ParticleEffectUtils;

public class Block extends EffectType {
	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.sendMessage(ChatColor.GREEN + "You can now block attacks!");
		}
	}
	
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, Effect effect) {
		if(new Random().nextInt(101) <= effect.getAmplifier()) {
			event.setCancelled(true);
		
			if(event.getEntity() instanceof Player) {
				SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
				
				player.sendMessage(ChatColor.GREEN + "**Blocked**");
				ParticleEffectUtils.fireworkParticleShower(player.getPlayer().getLocation(), Color.GREEN);
			} 
			
			if(event.getDamager() instanceof Player) {
				SNPlayer player = SNPlayer.getPlayer((Player) event.getDamager());
				
				player.sendMessage(ChatColor.RED + "**Enemy Blocked**");
			} else if(event.getDamager() instanceof Projectile) {
				SNPlayer player = SNPlayer.getPlayer((Player) ((Projectile) event.getDamager()).getShooter());
				
				player.sendMessage(ChatColor.RED + "**Enemy Blocked**");
			}
		}		
	}

	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.sendMessage(ChatColor.RED + "You can no longer block attacks!");				
		}
	}
}
