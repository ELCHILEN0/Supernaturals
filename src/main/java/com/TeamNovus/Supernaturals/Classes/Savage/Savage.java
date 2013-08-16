package com.TeamNovus.Supernaturals.Classes.Savage;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Savage.Classes.Vampire.Vampire;
import com.TeamNovus.Supernaturals.Classes.Savage.Classes.Werewolf.Werewolf;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Savage extends SNClass {

	public Savage(SNClass parentClass) {
		super("Savage", ChatColor.GRAY, 30, parentClass);
		
		setMaxHealth(1, 25);
		setMaxHealth(5, 26);
		setMaxHealth(10, 27);
		setMaxHealth(25, 29);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 40);
		setMaxFoodLevel(5, 45);
		setMaxFoodLevel(10, 50);
		setMaxFoodLevel(25, 55);
		setMaxFoodLevel(30, 60);
		
		setSpeed(1, 0.23f);
		setSpeed(5, 0.24f);
		setSpeed(10, 0.25f);
		setSpeed(25, 0.26f);
		setSpeed(30, 0.27f);
		
		setMaxMana(1, 10);
		setMaxMana(5, 15);
		setMaxMana(10, 20);
		setMaxMana(25, 25);
		setMaxMana(30, 30);
		
		addJoinableClass(25, new Vampire(this));
		addJoinableClass(25, new Werewolf(this));
	}
	
}
