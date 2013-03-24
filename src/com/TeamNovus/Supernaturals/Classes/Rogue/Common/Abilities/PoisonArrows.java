package com.TeamNovus.Supernaturals.Classes.Rogue.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityByProjectileEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class PoisonArrows extends Ability {
	private Integer duration;
	private Integer chance;

	public PoisonArrows(String name, String desc, Integer amplifier, Integer duration, Integer chance) {
		super(name, desc, amplifier);
		
		this.duration = duration;
		this.chance = chance;
	}
	
	public void onEntityDamageEntityByProjectile(EntityDamageEntityByProjectileEvent event) {
		if(new Random().nextInt(101) <= chance) {
			if(event.getDamaged() instanceof LivingEntity) {
				((LivingEntity) event.getDamaged()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, getAmplifier()));

				if(event.getEntity() instanceof Player) {
					((Player) event.getEntity()).sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Enemy Poisioned!");
				}
				
				if(event.getDamaged() instanceof Player) {
					((Player) event.getDamaged()).sendMessage(ChatColor.ITALIC + "" + ChatColor.RED + "You have been poisioned!");
				}
			}
		}
	}
}