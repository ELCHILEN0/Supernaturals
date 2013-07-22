package com.TeamNovus.Supernaturals.Classes.Savage.Classes.Vampire;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Vampire extends SNClass {

	public Vampire(SNClass parentClass) {
		super("Vampire", ChatColor.GRAY, 30, parentClass);
		
		setMaxHealth(1, 17);
		setMaxHealth(5, 19);
		setMaxHealth(10, 20);
		setMaxHealth(25, 22);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 10);
		setMaxFoodLevel(5, 12);
		setMaxFoodLevel(10, 13);
		setMaxFoodLevel(25, 14);
		setMaxFoodLevel(30, 15);
		
		setSpeed(1, .25f);
		setSpeed(5, .26f);
		setSpeed(10, .27f);
		setSpeed(25, .28f);
		setSpeed(30, .30f);	
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(25, 35);
		setMaxMana(30, 40);	
	}

}
