package com.TeamNovus.Supernaturals.Classes.Warrior.Classes.Lancer;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Lancer extends SNClass {

	public Lancer(SNClass parentClass) {
		super("Lancer", ChatColor.GOLD, 30, parentClass);
		
		setMaxHealth(1, 20);
		setMaxHealth(5, 21);
		setMaxHealth(10, 22);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 23);
		setMaxFoodLevel(10, 25);
		setMaxFoodLevel(25, 27);
		setMaxFoodLevel(30, 30);
		
		setSpeed(1, 0.23f);
		setSpeed(5, 0.24f);
		setSpeed(10, 0.25f);
		setSpeed(25, 0.26f);
		setSpeed(30, 0.27f);
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(25, 35);
		setMaxMana(30, 40);
	}
}
