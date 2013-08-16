package com.TeamNovus.Supernaturals.Classes.Warrior.Classes.Knight;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Knight extends SNClass {

	public Knight(SNClass parentClass) {
		super("Knight", ChatColor.GOLD, 30, parentClass);
		
		setMaxHealth(1, 25);
		setMaxHealth(5, 26);
		setMaxHealth(10, 27);
		setMaxHealth(25, 28);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 21);
		setMaxFoodLevel(10, 22);
		setMaxFoodLevel(25, 23);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 0.22f);
		setSpeed(5, 0.23f);
		setSpeed(10, 0.24f);
		setSpeed(25, 0.25f);
		setSpeed(30, 0.26f);
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(25, 35);
		setMaxMana(30, 40);
	}

}
