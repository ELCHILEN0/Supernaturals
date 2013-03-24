package com.TeamNovus.Supernaturals.Classes.Brute.Common.Powers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_5_R1.EntityCreature;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_5_R1.entity.CraftMonster;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.Entity.Effect;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Roar extends Power {
	private Integer range;
	
	public Roar(String name, String desc, Integer range, Integer amplifier, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, amplifier, cooldown, required, consume);
		
		this.range = range;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		List<Entity> entities = player.getPlayer().getNearbyEntities(range, range, range);
		List<Monster> monsters = new ArrayList<Monster>();
		
		for (Entity e : entities) {
			if (e instanceof Monster) {				
				monsters.add((Monster) e);
			}
		}
		
		for (int i = 0; i < monsters.size(); i++) {
			int next = i+1;
			
			if (next >= monsters.size()) {
				next = 0;
			}
						
			Monster entity = monsters.get(i);
			
			EntityCreature ec = ((CraftMonster) entity).getHandle();
			ec.setGoalTarget(null);
			
			SNEntity e = SNEntities.i.get(entity);
			e.addEffect(new IgnoreEntity(player.getPlayer()));
		}
		
		player.sendMessage(ChatColor.GREEN + "The monsters have second thoughts about chasing you!");

		return true;
	}
	

	public class IgnoreEntity extends Effect {
		private LivingEntity entity;
		
		public IgnoreEntity(LivingEntity entity) {
			this.entity = entity;
		}
		
		public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) {
			if(event.getTarget().equals(entity)) {
				event.setCancelled(true);
				event.setTarget(null);
			}
		}
	}
}

