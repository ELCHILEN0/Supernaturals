package com.TeamNovus.Supernaturals.Classes.Brute.Common.Powers;

import org.bukkit.Location;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Explode extends Power {
	
	public Explode(String name, String desc,
			Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
	}

	public Boolean cast(SNPlayer player) {
		Location loc = player.getPlayer().getLocation();
		
		loc.getWorld().createExplosion(loc.add(4, 0, 0), 5);
		loc.getWorld().createExplosion(loc.add(0, 0, 4), 5);
		loc.getWorld().createExplosion(loc.add(-4, 0, 0), 5);
		loc.getWorld().createExplosion(loc.add(0, 0, -4), 5);
		
		return true;
	}
	
}
