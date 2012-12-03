package com.TeamNovus.SupernaturalRaces.Effects;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Util.EffectUtil;

public class PhaseWalkEffect implements SNEventListener {

	@SNEventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "You leave your body!");
		}
	}
	
	@SNEventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, SNEffect effect) {
		if(new Random().nextInt(101) < (effect.getAmplifier() + 1) * 10) {
			if(event.getEntity() instanceof Player) {
				((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "Dodged!");
			}
			
			if(event.getDamager() instanceof Player) {
				((Player) event.getDamager()).sendMessage(ChatColor.GREEN + "Your enemy dodges your attack!");
			}
			
			event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.SMOKE, 20);
			
			event.setCancelled(true);
		}
	}
	
	@SNEventHandler
	public void onEffectTick(EffectTickEvent event) {
		if(event.getEntity() instanceof LivingEntity) {
			EffectUtil.addPotionGraphicalEffect((LivingEntity) event.getEntity(), 0x0000FF, 40);
		}
	}
	
	@SNEventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You can no longer walk on water!");
		}
	}
	
}
