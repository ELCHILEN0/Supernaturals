package com.TeamNovus.Supernaturals.Classes.Mage;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Mage.Classes.Necromancer.Necromancer;
import com.TeamNovus.Supernaturals.Classes.Mage.Classes.Wizard.Wizard;
import com.TeamNovus.Supernaturals.Classes.Mage.Powers.Freeze;
import com.TeamNovus.Supernaturals.Classes.Mage.Powers.Leech;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Mage extends SNClass {

	public Mage(SNClass parentClass) {
		super("Mage", ChatColor.DARK_PURPLE, 30, parentClass);
		
		setMaxHealth(1, 15);
		setMaxHealth(5, 17);
		setMaxHealth(10, 20);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 18);
		setMaxFoodLevel(5, 19);
		setMaxFoodLevel(10, 20);
		setMaxFoodLevel(25, 21);
		setMaxFoodLevel(30, 22);
		
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
		
		addJoinableClass(25, new Wizard(this));
		addJoinableClass(25, new Necromancer(this));
				
//		addAbility(15, new Ability(EffectType.SHIELD, "Shield", "Small chance to take no damage from attacks!", 6));
		addPower(20, new Freeze("Freeze", "Temporarily encase your enemies in ice!", 60, new Reagent(30), new Reagent(30)).setRange(50).setRadius(3).setDuration(5));
		addPower(30, new Leech("Drain", "Damage your enemies and heal yourself!", 30, new Reagent(20), new Reagent(20)).setRange(50).setAmount(5));

	}
	
}
