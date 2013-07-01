package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Blind extends Power {
	private Integer radius;
	private Integer duration;
	
	public Blind(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		radius = 5;
		duration = 30 * 20;
	}

	public Blind setRadius(Integer radius) {
		this.radius = radius;
		
		return this;
	}
	
	public Blind setDuration(Integer duration) {
		this.duration = duration;
		
		return this;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		int count = 0;
		
		for(Entity e : player.getPlayer().getNearbyEntities(radius, radius, radius)) {
			if(e instanceof LivingEntity) {
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 3));
			}
			
			count++;
		}
		
		if(count == 0) {
			player.sendMessage(ChatColor.RED + "No enemies were found in your radius of " + ChatColor.YELLOW + radius + ChatColor.RED + "!");
			return false;
		}
		
		player.sendMessage(ChatColor.YELLOW + "" + count +  "" +ChatColor.GREEN + " enemies were made blind!");
		return true;
	}



}
