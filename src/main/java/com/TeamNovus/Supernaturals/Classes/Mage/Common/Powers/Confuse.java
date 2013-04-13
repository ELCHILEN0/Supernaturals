package com.TeamNovus.Supernaturals.Classes.Mage.Common.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Confuse extends Power {
	private Integer range;
	private Integer duration;
	
	public Confuse(String name, String desc, Integer range, Integer duration, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
	}

	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		
		if(target == null) {
			player.sendMessage(ChatColor.RED + "No entities found within your range (" + range + ")!");
			return false;
		}
		
		target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, duration, 0));
		
		if(target instanceof Player) {
			((Player) target).sendMessage(ChatColor.RED + "You were confuzzled by " + ChatColor.YELLOW + player.getName() + ChatColor.RED + "!");
		}
		
		player.sendMessage(ChatColor.GREEN + "You confused " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
		
		return true;
	}
	
	

}
