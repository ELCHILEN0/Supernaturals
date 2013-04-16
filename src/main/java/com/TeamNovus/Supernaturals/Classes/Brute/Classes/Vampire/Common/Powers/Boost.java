package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Boost extends Power {
	private Integer duration;
	
	public Boost(String name, String desc, Integer cooldown, Integer duration, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.duration = duration;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration * 20, 3));
		return true;
	}

}
