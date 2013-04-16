package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Abilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.Entity.Effect;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class VampiricBite extends Ability {
	private Integer chance;
	private Integer duration;
	private Integer damage;

	public VampiricBite(String name, String desc, Integer chance, Integer duration, Integer damage) {
		super(name, desc, 0);

		this.chance = chance;
		this.duration = duration;
		this.damage = damage;
	}

	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {	
			SNEntity damaged = SNEntities.i.get((LivingEntity) event.getDamaged());

			damaged.addEffect(new VampiricBleed(duration, damage));

			if(event.getEntity() instanceof Player) {
				((Player) event.getEntity()).sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "You bit your enemy causing them to bleed!");
			}

			if(event.getDamaged() instanceof Player) {
				((Player) event.getDamaged()).sendMessage(ChatColor.ITALIC + "" + ChatColor.RED + "You have been bit and are now bleeding!");
			}
		}
	}


	public class VampiricBleed extends Effect {
		private Integer damage;

		public VampiricBleed(Integer duration, Integer damage) {			
			setDuration(duration);
			setPeriod(5);

			this.damage = damage;
		}

		public void onEffectTick(EntityEffectTickEvent event) {
			if(event.getEntity() instanceof Damageable) {
				((Damageable) event.getEntity()).damage(damage);
			}
		}
	}
}
