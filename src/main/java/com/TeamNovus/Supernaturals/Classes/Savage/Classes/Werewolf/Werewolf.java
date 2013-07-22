package com.TeamNovus.Supernaturals.Classes.Savage.Classes.Werewolf;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Werewolf extends SNClass {

	public Werewolf(SNClass parentClass) {
		super("Werewolf", ChatColor.DARK_GRAY, 30, parentClass);
		
		setMaxHealth(1, 20);
		setMaxHealth(5, 21);
		setMaxHealth(10, 23);
		setMaxHealth(25, 24);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 10);
		setMaxFoodLevel(5, 12);
		setMaxFoodLevel(10, 13);
		setMaxFoodLevel(25, 14);
		setMaxFoodLevel(30, 15);
		
		setSpeed(1, .22f);
		setSpeed(5, .23f);
		setSpeed(10, .24f);
		setSpeed(25, .25f);
		setSpeed(30, .26f);
		
		setMaxMana(1, 30);
		setMaxMana(5, 35);
		setMaxMana(10, 40);
		setMaxMana(25, 45);
		setMaxMana(30, 50);
	}

}
