package com.TeamNovus.Supernaturals.Classes.Barbarian.Classes.Brute;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Brute extends SNClass {

	public Brute(SNClass parentClass) {
		super("Brute", ChatColor.DARK_RED, 30, parentClass);
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(25, 25);
		setMaxMana(30, 40);
		
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
		
		setSpeed(1, 0.17f);
		setSpeed(5, 0.16f);
		setSpeed(10, 0.15f);
		setSpeed(25, 0.14f);
		setSpeed(30, 0.13f);
	}

}
