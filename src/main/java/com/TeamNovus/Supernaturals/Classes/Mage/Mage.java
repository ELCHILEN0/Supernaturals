package com.TeamNovus.Supernaturals.Classes.Mage;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Mage extends SNClass {

	public Mage(SNClass parentClass) {
		super("Mage", ChatColor.DARK_PURPLE, 30, parentClass);
		
		setMaxMana(1, 40);
		setMaxMana(5, 45);
		setMaxMana(10, 50);
		setMaxMana(25, 55);
		setMaxMana(30, 60);
		
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
		
		setSpeed(1, 0.2f);
		setSpeed(5, 0.21f);
		setSpeed(10, 0.22f);
		setSpeed(25, 0.23f);
		setSpeed(30, 0.24f);
	}
	
}
