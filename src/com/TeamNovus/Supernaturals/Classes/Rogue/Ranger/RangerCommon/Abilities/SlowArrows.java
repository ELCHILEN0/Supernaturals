package com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Abilities;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityByProjectileEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class SlowArrows extends Ability {
	private Integer duration;
	private Integer chance;

	public SlowArrows(String name, String desc, Integer amplifier, Integer duration, Integer chance) {
		super(name, desc, amplifier);
		this.duration = duration;
		this.chance = chance;
	}
	
	public void onEntityDamageEntityByProjectile(EntityDamageEntityByProjectileEvent event) {
		if(new Random().nextInt(101) <= chance) {
			System.out.println("chance met!");
			
			if(event.getDamaged() instanceof LivingEntity) {
				System.out.println("chance exect!!");

				((LivingEntity) event.getDamaged()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, amplifier));
			}
		}
	}

}
