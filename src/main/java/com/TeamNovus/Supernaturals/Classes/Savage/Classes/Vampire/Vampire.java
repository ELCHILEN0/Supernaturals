package com.TeamNovus.Supernaturals.Classes.Savage.Classes.Vampire;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Vampire extends SNClass {

	public Vampire(SNClass parentClass) {
		super("Vampire", ChatColor.GRAY, 30, parentClass);
		
		setMaxHealth(1, 17);
		setMaxHealth(5, 19);
		setMaxHealth(10, 20);
		setMaxHealth(20, 22);
		setMaxHealth(30, 25);
		
		setMaxMana(1, 30);
		setMaxMana(5, 35);
		setMaxMana(10, 40);
		setMaxMana(20, 45);
		setMaxMana(30, 50);
		
		setMaxFoodLevel(1, 10);
		setMaxFoodLevel(5, 12);
		setMaxFoodLevel(20, 13);
		setMaxFoodLevel(30, 15);
		
		setSpeed(1, .25f);
		setSpeed(5, .26f);
		setSpeed(20, .28f);
		setSpeed(30, .30f);		
	}

}
