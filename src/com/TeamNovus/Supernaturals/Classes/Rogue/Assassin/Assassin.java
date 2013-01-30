package com.TeamNovus.Supernaturals.Classes.Rogue.Assassin;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Assassin extends SNClass {

	public Assassin(SNClass parentClass) {
		super("Assassin", ChatColor.DARK_GRAY, 30, parentClass);
		
		setSpeed(1, 0.3f);

		setMaxHealth(1, 17);

		setMaxFoodLevel(1, 40);
	}

}
