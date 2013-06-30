package com.TeamNovus.Supernaturals.Classes.Savage.Classes.Werewolf;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Player.SNClass;

public class Werewolf extends SNClass {

	public Werewolf() {
		super("Werewolf", ChatColor.DARK_GRAY, 30);
		
		setMaxHealth(1, 20);
		setMaxHealth(5, 21);
		setMaxHealth(10, 23);
		setMaxHealth(20, 24);
		setMaxHealth(30, 25);
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(20, 25);
		setMaxMana(30, 40);
		
		setMaxFoodLevel(1, 10);
		setMaxFoodLevel(5, 12);
		setMaxFoodLevel(20, 13);
		setMaxFoodLevel(30, 15);
		
		setSpeed(1, .22f);
		setSpeed(5, .23f);
		setSpeed(20, .24f);
		setSpeed(30, .25f);			}

}
