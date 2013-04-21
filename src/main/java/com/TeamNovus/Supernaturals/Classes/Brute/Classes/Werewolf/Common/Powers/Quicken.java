package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Powers;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Quicken extends Power {
	private Integer duration;

	public Quicken(String name, String desc, Integer cooldown, Integer duration, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.duration = duration;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {		
		player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * duration, 3));
		
		return true;
	}
}
