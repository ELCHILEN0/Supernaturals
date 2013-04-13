package com.TeamNovus.Supernaturals.Classes.Mage.Common.Powers;

import java.util.Random;

import org.bukkit.Location;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Thunderstorm extends Power {
	private Integer range;
	private Integer bolts;
	
	public Thunderstorm(String name, String desc, Integer range, Integer bolts, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		this.range = range;
		this.bolts = bolts;
	}

	public Boolean cast(SNPlayer player) {
		Random random = new Random();
		
		for (int i = 0; i <= bolts; i++) {
			player.getPlayer().getWorld().strikeLightning(new Location(player.getPlayer().getWorld(), random.nextInt(range + 1) - range/2, player.getPlayer().getLocation().getY() - 10, random.nextInt(range + 1) - range/2));
		}
		
		player.sendMessage("Lightning falls all around you!");
		
		return true;
	}

}
