package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Util.EffectUtil;

public class VanishEffect implements SNEventListener {

	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You are now invisible!");
		}
	}
	
	@SNEventHandler
	public void onEffectTick(EffectTickEvent event) {
		if(event.getEntity() instanceof LivingEntity) {
			EffectUtil.addPotionGraphicalEffect((LivingEntity) event.getEntity(), 0xCCCCCC, 40);
		}
	}
	
	@SNEventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event, SNEffect effect) {
		int radius = effect.getAmplifier() * 16;
		for(Entity e : event.getPlayer().getNearbyEntities(radius, radius, radius)) {
			if(e instanceof Player) {
				((Player) e).hidePlayer(event.getPlayer());
			}
		}
	}
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.showPlayer(((Player) event.getEntity()));
			}
									
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You are no longer invisible!");
		}
	}
	
}
