package com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Tornado extends Power {
	public Integer range;
	
	public Tornado(String name, String desc, Integer range, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
	}
	
	public Boolean cast(SNPlayer player) {
		Random random = new Random();
		
		int total = 0;
		
		for(Entity target : player.getPlayer().getNearbyEntities(range, range, range)) {
			if(target instanceof LivingEntity) {
				total++;
				
				target.getVelocity().multiply(new Vector(random.nextInt(), 1, random.nextInt()));
				
				if(target instanceof Player) {
					((Player) target).sendMessage(ChatColor.RED + "A tornado sweeps through the world!");
				}
			}
		}
		
		player.sendMessage(ChatColor.GREEN + "Your tornado threw " + ChatColor.YELLOW + total + ChatColor.GRAY + " enemies away!");
		
		return true;
	}
}
