package com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer.Powers.Ensnare;
import com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer.Powers.Ignite;
import com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer.Powers.Leech;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Necromancer extends SNClass {

	public Necromancer(SNClass parentClass) {
		super("Necromancer", ChatColor.DARK_GRAY, 30, parentClass);
		
		setMaxHealth(1, 15);
		setMaxHealth(5, 17);
		setMaxHealth(10, 20);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 10);
		setMaxFoodLevel(5, 11);
		setMaxFoodLevel(10, 12);
		setMaxFoodLevel(25, 13);
		setMaxFoodLevel(30, 15);
		
		setSpeed(1, 0.22f);
		setSpeed(5, 0.23f);
		setSpeed(10, 0.24f);
		setSpeed(25, 0.25f);
		setSpeed(30, 0.26f);
		
		setMaxMana(1, 35);
		setMaxMana(5, 40);
		setMaxMana(10, 45);
		setMaxMana(25, 50);
		setMaxMana(30, 55);
		
		addPower(1, new Ensnare("Ensnare", "Trap your enemies in cobwebs!", 60, new Reagent(25)).setDuration(15 * 20).setRadius(2).setRange(100));
		addPower(1, new Ignite("Ignite", "Light your enemies on fire!", 30, new Reagent(15)).setRange(25).setDuration(20 * 15));
		addPower(1, new Leech("Life Steal", "Steal the life of your enemies!", 30, new Reagent(15)).setAmount(8).setRange(25));
	}

}
