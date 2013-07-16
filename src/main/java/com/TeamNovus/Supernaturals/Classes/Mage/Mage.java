package com.TeamNovus.Supernaturals.Classes.Mage;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Mage.Powers.Confuse;
import com.TeamNovus.Supernaturals.Classes.Mage.Powers.Freeze;
import com.TeamNovus.Supernaturals.Classes.Mage.Powers.Leech;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

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
		
		addPower(1, new Freeze("Freeze", "Encase your enemies in a ball of ice!", 20 * 60, new Reagent(30), new Reagent(30)).setRange(50).setRadius(3).setDuration(20 * 15));
		addPower(1, new Confuse("Confuse", "Disorient your enemies!", 20 * 30, new Reagent(15), new Reagent(15)).setRange(50).setDuration(20 * 5));
		addPower(1, new Leech("Leech", "Steal health from your enemies!", 20 * 30, new Reagent(20), new Reagent(20)).setRange(50).setAmount(4));
		
		addAbility(1, new Ability(EffectType.REVIVE, "Phoenix Rise", "Small chance to rise from the ashes!", 1));
		// Freeze, Leech, Meditate
		// Shield, Phoenix
	}
	
}
