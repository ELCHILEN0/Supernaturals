package com.TeamNovus.Supernaturals.Classes.Barbarian;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Barbarian.Classes.Bandit.Bandit;
import com.TeamNovus.Supernaturals.Classes.Barbarian.Classes.Brute.Brute;
import com.TeamNovus.Supernaturals.Classes.Barbarian.Powers.Mar;
import com.TeamNovus.Supernaturals.Classes.Barbarian.Powers.Quicken;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Barbarian extends SNClass {

	public Barbarian(SNClass parentClass) {
		super("Barbarian", ChatColor.DARK_RED, 30, parentClass);
		
		setMaxHealth(1, 25);
		setMaxHealth(5, 26);
		setMaxHealth(10, 27);
		setMaxHealth(25, 29);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 20);
		setMaxFoodLevel(5, 25);
		setMaxFoodLevel(10, 30);
		setMaxFoodLevel(25, 35);
		setMaxFoodLevel(30, 40);
		
		setSpeed(1, 0.165f);
		setSpeed(5, 0.17f);
		setSpeed(10, 0.18f);
		setSpeed(25, 0.185f);
		setSpeed(30, 0.19f);
		
		setMaxMana(1, 10);
		setMaxMana(5, 15);
		setMaxMana(10, 20);
		setMaxMana(25, 25);
		setMaxMana(30, 30);
		
		addJoinableClass(25, new Brute(this));
		addJoinableClass(25, new Bandit(this));
		
		addPower(15, new Mar("Mar", "Damage your oponents armor.", 120, new Reagent(15)).setRange(15).setDamage(20));
		addPower(20, new Quicken("Quicken", "Temporarily increase your speed.", 60, new Reagent(15)).setAmplifier(2).setDuration(15 * 20));
		addAbility(30, new Ability(EffectType.BARRAGE, "Tripple Strike", "Small chance to strike three times per attack.", 3));
	}

}
