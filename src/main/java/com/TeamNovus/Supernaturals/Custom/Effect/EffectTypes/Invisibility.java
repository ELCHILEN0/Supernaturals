package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Invisibility extends EffectType {

	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.hidePlayer(player.getPlayer());
			}
			
			player.sendMessage(ChatColor.GREEN + "You have become invisible!");
		}
	}
	
	public void onEffectTick(EntityEffectTickEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.hidePlayer(player.getPlayer());
			}
		}
	}
	
	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.showPlayer(player.getPlayer());
			}
			
			player.sendMessage(ChatColor.RED + "You are no longer invisible!");				
		}
	}

}
