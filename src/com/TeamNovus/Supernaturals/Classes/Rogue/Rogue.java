package com.TeamNovus.Supernaturals.Classes.Rogue;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers.Leap;
import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers.Sneak;
import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers.Vanish;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Rogue extends SNClass {
	
	public Rogue(SNClass parentClass) {
		super("Rogue", ChatColor.DARK_GREEN, 30, parentClass);
		
		addPower(1, new Leap("Leap", "Leap away from danger!", 15, 0, new Reagent(), new Reagent()));
		addPower(1, new Sneak("Sneak", "Hide from your enemies!", 60, 0, new Reagent(), new Reagent()));
		addPower(1, new Vanish("Vansh", "Completely vanish from your enemies!", 60, 0, new Reagent(), new Reagent()));
		
		setMaxHealth(1, 15);
		setMaxHealth(5, 17);
		setMaxHealth(10, 20);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 15);
		setMaxFoodLevel(5, 17);
		setMaxFoodLevel(10, 20);
		setMaxFoodLevel(25, 23);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 2.0f);
		setSpeed(5, 2.1f);
		setSpeed(10, 2.2f);
		setSpeed(25, 2.3f);
		setSpeed(30, 2.4f);
		
	}

}
