package com.TeamNovus.Supernaturals.Classes.Mage;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Mage extends SNClass {

	public Mage(SNClass parentClass) {
		super("Mage", ChatColor.DARK_PURPLE, 30, parentClass);
		
		setMaxHealth(1, 15);
		setMaxHealth(5, 17);
		setMaxHealth(10, 20);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 25);
		setMaxFoodLevel(5, 25);
		setMaxFoodLevel(10, 25);
		setMaxFoodLevel(25, 25);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 1.0f);
		setSpeed(5, 1.1f);
		setSpeed(10, 1.2f);
		setSpeed(25, 1.3f);
		setSpeed(30, 1.4f);
	}
	
}
