package com.TeamNovus.Supernaturals.Classes.Barbarian;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Barbarian extends SNClass {

	public Barbarian(SNClass parentClass) {
		super("Barbarian", ChatColor.DARK_RED, 30, parentClass);
		
		setMaxMana(1, 10);
		setMaxMana(5, 15);
		setMaxMana(10, 20);
		setMaxMana(25, 25);
		setMaxMana(30, 30);
		
		setMaxHealth(1, 25);
		setMaxHealth(5, 26);
		setMaxHealth(10, 27);
		setMaxHealth(25, 29);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 25);
		setMaxFoodLevel(10, 30);
		setMaxFoodLevel(25, 35);
		setMaxFoodLevel(30, 40);
		
		setSpeed(1, 0.165f);
		setSpeed(5, 0.17f);
		setSpeed(10, 0.18f);
		setSpeed(25, 0.185f);
		setSpeed(30, 0.19f);
	}

}
