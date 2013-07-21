package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Evasion extends EffectType {
	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.getPlayer().setSneaking(true);
			player.sendMessage(ChatColor.GREEN + "You have begun sneaking!");
		}
	}
	
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, Effect effect) {
		if(new Random(101).nextInt() < effect.getAmplifier()) {
			event.setCancelled(true);
		
			if(event.getEntity() instanceof Player) {
				SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
				
				player.getPlayer().setSneaking(true);
				player.sendMessage(ChatColor.GREEN + "Evaded!");
			} else if(event.getDamager() instanceof Player) {
				SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
				
				player.getPlayer().setSneaking(true);
				player.sendMessage(ChatColor.GREEN + "Your enemy evaded your blow!");
			} else if(event.getEntity() instanceof Projectile) {
				SNPlayer player = SNPlayers.i.get((Player) ((Projectile) event.getEntity()).getShooter());
				
				player.getPlayer().setSneaking(true);
				player.sendMessage(ChatColor.GREEN + "Your enemy evaded your shot!");
			}
		}		
	}

	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.getPlayer().setSneaking(true);
			player.sendMessage(ChatColor.RED + "You are no longer sneaking!");				
		}
	}
}