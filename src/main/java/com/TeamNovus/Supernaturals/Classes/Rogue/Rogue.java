package com.TeamNovus.Supernaturals.Classes.Rogue;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer.Archer;
import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer.Powers.Sneak;
import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Assassin;
import com.TeamNovus.Supernaturals.Classes.Rogue.Powers.Lunge;
import com.TeamNovus.Supernaturals.Classes.Rogue.Powers.Quicken;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Rogue extends SNClass {
	
	public Rogue(SNClass parentClass) {
		super("Rogue", ChatColor.DARK_GREEN, 30, parentClass);
		
		setMaxHealth(1, 15);
		setMaxHealth(5, 17);
		setMaxHealth(10, 20);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 15);
		setMaxFoodLevel(5, 17);
		setMaxFoodLevel(10, 20);
		setMaxFoodLevel(25, 23);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 0.3f);
		setSpeed(5, 0.31f);
		setSpeed(10, 0.32f);
		setSpeed(25, 0.33f);
		setSpeed(30, 0.34f);
		
		setMaxMana(1, 30);
		setMaxMana(5, 35);
		setMaxMana(10, 40);
		setMaxMana(25, 45);
		setMaxMana(30, 50);
		
		addPower(1, new Sneak("Sneak", "Hide your nametag from enemies for 30 seconds.", 20 * 30, new Reagent(15), new Reagent(15)).setDuration(20 * 30));
		addPower(10, new Lunge("Lunge", "Lunge to a far away location!", 20 * 10, new Reagent(10), new Reagent(10)).setMaxDistance(50));
		addPower(30, new Quicken("Quicken", "Temporarily give yourself a speed booost!", 20 * 60, new Reagent(20), new Reagent(20)).setDuration(20 * 30).setAmplifier(2));
		
		addAbility(5, new Ability(EffectType.EVASION, "Evade", "Occasionally evade your enemies blows!", 3));
		addAbility(25, new Ability(EffectType.POISON_ARROW, "Poison Arrow", "Dip your arrows in poison occasionally poisioning your enemies!", 5));
		
		addJoinableClass(25, new Archer(this));
		addJoinableClass(25, new Assassin(this));
	}

}
