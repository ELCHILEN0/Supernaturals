package com.TeamNovus.Supernaturals.Classes.Mage.Classes.Wizard;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Wizard extends SNClass {

	public Wizard(SNClass parentClass) {
		super("Wizard", ChatColor.LIGHT_PURPLE, 30, parentClass);
		
		setMaxHealth(1, 18);
		setMaxHealth(5, 20);
		setMaxHealth(10, 23);
		setMaxHealth(25, 25);
		setMaxHealth(30, 27);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 21);
		setMaxFoodLevel(10, 22);
		setMaxFoodLevel(25, 23);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 0.2f);
		setSpeed(5, 0.21f);
		setSpeed(10, 0.22f);
		setSpeed(25, 0.23f);
		setSpeed(30, 0.24f);
		
		setMaxMana(1, 40);
		setMaxMana(5, 45);
		setMaxMana(10, 50);
		setMaxMana(25, 55);
		setMaxMana(30, 60);
	}
}
