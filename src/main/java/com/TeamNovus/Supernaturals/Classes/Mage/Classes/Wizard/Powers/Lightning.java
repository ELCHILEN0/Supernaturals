package com.TeamNovus.Supernaturals.Classes.Mage.Classes.Wizard.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Lightning extends Power {
	private int range;
	private int bolts;
	
	public Lightning(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		range = 256;
		bolts = 30;
	}
	
	public Lightning(String name, String desc, Integer cooldown, Reagent reagent) {
		this(name, desc, cooldown, reagent, reagent);
	}
	
	public Lightning setRange(int range) {
		this.range = range;
		
		return this;
	}
	
	public Lightning setBolts(int bolts) {
		this.bolts = bolts;
		
		return this;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity target = player.getTargetEntity(range);
		
		if(target != null) {
			for (int i = 0; i < bolts; i++) {
				target.getWorld().strikeLightningEffect(target.getLocation());
				target.damage(5.0, player.getPlayer());
			}
			
			return true;
		}
		
		
		player.sendMessage(ChatColor.RED + "No player was found within your range of " + ChatColor.YELLOW + range + ChatColor.RED + " blocks!");
		return false;
	}

}
