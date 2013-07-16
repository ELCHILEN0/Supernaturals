package com.TeamNovus.Supernaturals.Classes.Mage.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Leech extends Power {
	private int range;
	private int amount;
	
	public Leech(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		range = 256;
		amount = 4;
	}
	
	public Leech setRange(int range) {
		this.range = range;
		
		return this;
	}
	
	public Leech setAmount(int amount) {
		this.amount = amount;
		
		return this;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		
		if(target != null) {
			target.damage(amount, player.getPlayer());
			player.setHealth(player.getHealth() + amount);
			
			if(target instanceof Player) {
				SNPlayer t = SNPlayers.i.get((Player) target);
				
				t.sendMessage("You feel your health being drained by an enemy!");
			}
			
			player.sendMessage("You feel rejuvenated and resume fighting with new vigor!");
			return true;
		}
		
		
		player.sendMessage(ChatColor.RED + "No player was found within your range of " + ChatColor.YELLOW + range + ChatColor.RED + " blocks!");
		return false;
	}

}
