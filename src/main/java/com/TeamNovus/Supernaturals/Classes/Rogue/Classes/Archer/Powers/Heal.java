package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer.Powers;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Heal extends Power {
	private Integer amplifier;
	
	public Heal(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		amplifier = 1;
	}

	public Heal setAmplifier(Integer amplifier) {
		this.amplifier = amplifier;
		
		return this;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		boolean healed = false;
		
		if(player.getHealth() < player.getMaxHealth()) {
			player.setHealth(player.getHealth() + amplifier * 3 > player.getMaxHealth() ? player.getMaxHealth() : player.getHealth() + amplifier * 3);			
			
			healed = true;
		}
		
		if(player.getFoodLevel() < player.getMaxFoodLevel()) {
			player.setFoodLevel(player.getFoodLevel() + amplifier * 3 > player.getMaxFoodLevel() ? player.getMaxFoodLevel() : player.getFoodLevel() + amplifier * 3);			
			
			healed = true;
		}
		
		if(healed) {
			player.sendMessage(ChatColor.GREEN + "You sucessfully healed yourself!");
		} else {
			player.sendMessage(ChatColor.RED + "You were not in need of being healed!");
		}
		
		return healed;
	}



}
