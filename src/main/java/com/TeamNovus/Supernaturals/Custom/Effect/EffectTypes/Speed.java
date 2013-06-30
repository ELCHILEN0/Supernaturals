package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Speed extends EffectType {

	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.getPlayer().setSneaking(true);
			player.sendMessage(ChatColor.GREEN + "You have been given a speed boost!");
		}
	}
	
	public void onEffectTick(EntityEffectTickEvent event, Effect effect) {
		if(event.getEntity() instanceof LivingEntity) {
			((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, effect.getAmplifier()));
		}
	}
	
	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.getPlayer().setSneaking(true);
			player.sendMessage(ChatColor.RED + "Your speed boost has worn off!");
		}
	}

}
