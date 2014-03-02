package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectTypeListener;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityByProjectileEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Util.ParticleEffectUtils;

public class PoisonArrow extends EffectType {
	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.sendMessage(ChatColor.GREEN + "Your arrows can poison enemies!");
		}
	}
	
	@EffectTypeListener
	public void onEntityDamageEntityByProjectile(EntityDamageEntityByProjectileEvent event, Effect effect) {
		if(new Random().nextInt(101) <= effect.getAmplifier()) {
			if(event.getDamaged() instanceof LivingEntity) {
				LivingEntity target = (LivingEntity) event.getDamaged();

				target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 3 * effect.getAmplifier(), effect.getAmplifier()));
				
				if(target instanceof Player) {				
					((Player) target).sendMessage(ChatColor.RED + "**Poisoned**");
				}
				
				ParticleEffectUtils.fireworkParticleShower(target.getLocation(), Color.RED);

				if(event.getEntity() instanceof Player) {				
					((Player) event.getEntity()).sendMessage(ChatColor.GREEN + "**Poisoned Enemy**");
				}
			}
		}
	}

	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.sendMessage(ChatColor.RED + "Your arrows no longer poison enemies!");				
		}
	}
}
