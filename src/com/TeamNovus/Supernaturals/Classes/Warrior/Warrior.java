package com.TeamNovus.Supernaturals.Classes.Warrior;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Warrior extends SNClass {

	public Warrior(SNClass parentClass) {
		super("Warrior", ChatColor.GOLD, 30, parentClass);
		
		setMaxHealth(1, 20);
		setMaxHealth(5, 22);
		setMaxHealth(10, 24);
		setMaxHealth(25, 27);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 23);
		setMaxFoodLevel(10, 25);
		setMaxFoodLevel(25, 27);
		setMaxFoodLevel(30, 30);
		
		setSpeed(1, 1.0f);
		setSpeed(5, 1.0f);
		setSpeed(10, 1.0f);
		setSpeed(25, 1.0f);
		setSpeed(30, 1.0f);
	}
	
}
