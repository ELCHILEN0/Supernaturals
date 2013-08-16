package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer.Powers;

import org.bukkit.entity.Arrow;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Barrage extends Power {
	private Integer arrows;
	
	public Barrage(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		arrows = 10;
	}

	public Barrage setAmplifier(Integer arrows) {
		this.arrows = arrows;
		
		return this;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		for (int i = 0; i < arrows; i++) {
			player.getPlayer().launchProjectile(Arrow.class);
		}
		
		return true;
	}



}
