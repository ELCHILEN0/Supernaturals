package com.TeamNovus.Supernaturals.Classes.Mage.Powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Confuse extends Power {
	private int range;
	private int duration;
	
	public Confuse(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		range = 256;
		duration = 5;
	}
	
	public Confuse setRange(int range) {
		this.range = range;
		
		return this;
	}
	
	public Confuse setDuration(int duration) {
		this.duration = duration;
		
		return this;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		LivingEntity t = player.getTargetEntity(range);
		
		if(t != null && t instanceof Player) {
			SNPlayer target = SNPlayer.getPlayer((Player) t);
			target.getEntity().addEffect(new Effect(EffectType.CONFUSION, duration, 3));
			
			player.sendMessage(ChatColor.GREEN + "You have confused your enemy!");
			return true;
		}
		
		
		player.sendMessage(ChatColor.RED + "No player was found within your range of " + ChatColor.YELLOW + range + ChatColor.RED + " blocks!");
		return false;
	}

}
