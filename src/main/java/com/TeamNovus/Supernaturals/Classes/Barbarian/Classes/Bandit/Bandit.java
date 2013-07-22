package com.TeamNovus.Supernaturals.Classes.Barbarian.Classes.Bandit;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Bandit extends SNClass {

	public Bandit(SNClass parentClass) {
		super("Bandit", ChatColor.GOLD, 30, parentClass);
		
		setMaxHealth(1, 23);
		setMaxHealth(5, 24);
		setMaxHealth(10, 25);
		setMaxHealth(25, 26);
		setMaxHealth(30, 27);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 25);
		setMaxFoodLevel(10, 30);
		setMaxFoodLevel(25, 35);
		setMaxFoodLevel(30, 40);
		
		setSpeed(1, 0.17f);
		setSpeed(5, 0.18f);
		setSpeed(10, 0.19f);
		setSpeed(25, 0.20f);
		setSpeed(30, 0.21f);
		
		setMaxMana(1, 10);
		setMaxMana(5, 15);
		setMaxMana(10, 20);
		setMaxMana(25, 25);
		setMaxMana(30, 30);
	}

}
