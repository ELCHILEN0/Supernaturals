package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Sneak extends EffectType {

	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.getPlayer().setSneaking(true);
			player.sendMessage(ChatColor.GREEN + "You have begun sneaking!");
		}
	}
	
	public void onEffectTick(EntityEffectTickEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.getPlayer().setSneaking(true);
		}
	}
	
	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.getPlayer().setSneaking(false);
			player.sendMessage(ChatColor.RED + "You are no longer sneaking!");				
		}
	}

}
