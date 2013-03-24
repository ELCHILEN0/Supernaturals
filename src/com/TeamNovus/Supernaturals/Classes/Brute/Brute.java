package com.TeamNovus.Supernaturals.Classes.Brute;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Brute.Common.Powers.Earthquake;
import com.TeamNovus.Supernaturals.Classes.Brute.Common.Powers.Explode;
import com.TeamNovus.Supernaturals.Classes.Brute.Common.Powers.Roar;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Brute extends SNClass {

	public Brute(SNClass parentClass) {
		super("Brute", ChatColor.DARK_RED, 30, parentClass);
		
		addPower(1, new Roar("Roar", "", 32, 0, new Reagent(), new Reagent()));
		addPower(1, new Explode("Explode", "Trigger explosions around you!", 0, new Reagent(), new Reagent()));
		addPower(1, new Earthquake("Earthquake", "Cause the ground to shake with fury!", 0, new Reagent(), new Reagent()));
		
		setMaxHealth(1, 30);
		setMaxHealth(5, 33);
		setMaxHealth(10, 35);
		setMaxHealth(25, 37);
		setMaxHealth(30, 40);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 19);
		setMaxFoodLevel(10, 18);
		setMaxFoodLevel(25, 17);
		setMaxFoodLevel(30, 16);
		
		setSpeed(1, 0.1f);
		setSpeed(5, 0.09f);
		setSpeed(10, 0.08f);
		setSpeed(25, 0.07f);
		setSpeed(30, 0.06f);
	}

}
