package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Leech extends Power {
	private Integer range;
	
	public Leech(String name, String desc, Integer cooldown, Integer range, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
	}

	@Override
	public Boolean cast(final SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		if(target == null) {
			player.sendMessage(ChatColor.RED + "No entity was found within your range!");
			return false;
		}
		
		target.damage(4, player.getPlayer());
		player.setHealth(player.getHealth() + 4);
		
		if(target instanceof Player)
			((Player) target).sendMessage(ChatColor.ITALIC + "" + ChatColor.YELLOW + player.getName() + ChatColor.RED + " has leeched your life!");
		player.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "You have leeched life!");
		return true;
	}

}
