package com.TeamNovus.Supernaturals.Classes.Warrior;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Warrior.Classes.Knight.Knight;
import com.TeamNovus.Supernaturals.Classes.Warrior.Classes.Lancer.Lancer;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Warrior extends SNClass {

	public Warrior(SNClass parentClass) {
		super("Warrior", ChatColor.GOLD, 30, parentClass);
		
		setMaxHealth(1, 20);
		setMaxHealth(5, 22);
		setMaxHealth(10, 24);
		setMaxHealth(25, 27);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 23);
		setMaxFoodLevel(10, 25);
		setMaxFoodLevel(25, 27);
		setMaxFoodLevel(30, 30);
		
		setSpeed(1, 0.2f);
		setSpeed(5, 0.21f);
		setSpeed(10, 0.22f);
		setSpeed(25, 0.23f);
		setSpeed(30, 0.24f);
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(25, 35);
		setMaxMana(30, 40);
		
		addJoinableClass(25, new Knight(this));
		addJoinableClass(25, new Lancer(this));
	}
	
}
