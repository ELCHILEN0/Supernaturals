package com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Strike extends Power {
	public Integer range;
	public Integer damage;
	
	public Strike(String name, String desc, Integer range, Integer damage, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
		this.damage = damage;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		
		if(target == null) {
			player.sendMessage(ChatColor.RED + "No entities found within your range (" + range + ")!");
			return false;
		}
		
		target.damage(damage, player.getPlayer());
		
		if(target instanceof Player) {
			((Player) target).sendMessage(ChatColor.RED + "You were struck by " + ChatColor.YELLOW + player.getName() + ChatColor.RED + "!");
		}
		
		player.sendMessage(ChatColor.GREEN + "You struck " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
		
		return true;
	}

}
