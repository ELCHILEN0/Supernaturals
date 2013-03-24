package com.TeamNovus.Supernaturals.Classes.Brute.Common.Powers;

import java.util.Random;

import org.bukkit.Location;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Earthquake extends Power {

	public Earthquake(String name, String desc, Integer amplifier,
			Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, amplifier, cooldown, required, consume);
	}

	@Override
	public Boolean cast(SNPlayer player) {
		Location loc = player.getPlayer().getLocation();
		
		Random randX = new Random();
		Random randZ = new Random();
		
		for (int i = 0; i < 7; i++) {
			loc.getWorld().createExplosion(loc.add(randX.nextInt(51)-25, 0, 0), randZ.nextInt(51)-25);
		}
		
		return true;
	}
	
}
