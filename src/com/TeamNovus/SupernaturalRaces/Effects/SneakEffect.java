package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Util.EffectUtil;

public class SneakEffect {

	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You are now sneaking!");
		}
	}
	
	@SNEventHandler
	public void onEffectTick(EffectTickEvent event) {
		if(event.getEntity() instanceof LivingEntity) {
			EffectUtil.addPotionGraphicalEffect((LivingEntity) event.getEntity(), 0x000000, 40);
		}
	}
	
	@SNEventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event, SNEffect effect) {
		event.getPlayer().setSneaking(true);
	}
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).setSneaking(false);
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You are no longer sneaking!");
		}
	}
	
}
