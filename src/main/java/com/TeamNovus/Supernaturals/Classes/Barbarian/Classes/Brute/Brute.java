package com.TeamNovus.Supernaturals.Classes.Barbarian.Classes.Brute;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Brute extends SNClass {

	public Brute(SNClass parentClass) {
		super("Brute", ChatColor.DARK_RED, 30, parentClass);
		
		setMaxMana(1, 15);
		setMaxMana(5, 20);
		setMaxMana(10, 25);
		setMaxMana(25, 30);
		setMaxMana(30, 35);
		
		setMaxHealth(1, 30);
		setMaxHealth(5, 32);
		setMaxHealth(10, 34);
		setMaxHealth(25, 37);
		setMaxHealth(30, 40);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 25);
		setMaxFoodLevel(10, 30);
		setMaxFoodLevel(25, 35);
		setMaxFoodLevel(30, 40);
		
		setSpeed(1, 0.13f);
		setSpeed(5, 0.14f);
		setSpeed(10, 0.15f);
		setSpeed(25, 0.16f);
		setSpeed(30, 0.17f);
	}

}
