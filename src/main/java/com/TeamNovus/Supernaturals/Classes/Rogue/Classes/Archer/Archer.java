package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer.Powers.Barrage;
import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Archer.Powers.Heal;
import com.TeamNovus.Supernaturals.Classes.Rogue.Powers.Sneak;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Archer extends SNClass {

	public Archer(SNClass parentClass) {
		super("Archer", ChatColor.DARK_GREEN, 30, parentClass);
		
		setMaxHealth(1, 18);
		setMaxHealth(5, 20);
		setMaxHealth(10, 23);
		setMaxHealth(25, 25);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 17);
		setMaxFoodLevel(5, 20);
		setMaxFoodLevel(10, 23);
		setMaxFoodLevel(25, 25);
		setMaxFoodLevel(30, 27);
		
		setSpeed(1, 0.27f);
		setSpeed(5, 0.28f);
		setSpeed(10, 0.29f);
		setSpeed(25, 0.30f);
		setSpeed(30, 0.31f);
		
		setMaxMana(1, 40);
		setMaxMana(5, 45);
		setMaxMana(10, 50);
		setMaxMana(25, 55);
		setMaxMana(30, 60);
		
		addPower(1, new Heal("Heal", "Quickly heal yourself in times of need!", 20 * 30, new Reagent(10), new Reagent(10)).setAmplifier(2));
		addPower(10, new Sneak("Sneak", "Hide your nametag from your enemies!", 60 * 20, new Reagent(15), new Reagent(15)).setDuration(30 * 20));
		addPower(30, new Barrage("Barrage", "Send a volley of arrows at your enemies!!", 60 * 20, new Reagent(15), new Reagent(15)));
		
		addAbility(5, new Ability(EffectType.BLINDING_ARROW, "Blinding Arrow", "Occasionally blind your enemies with your arrows!", 4));
		addAbility(25, new Ability(EffectType.POISON_ARROW, "Poison Arrow", "Occasionally poison your enemies with your arrows!", 4));
	}
	
}
