package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Powers.Blind;
import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Powers.Blink;
import com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Powers.Vanish;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Assassin extends SNClass {

	public Assassin(SNClass parentClass) {
		super("Assassin", ChatColor.DARK_GRAY, 30, parentClass);
		
		setMaxMana(1, 25);
		setMaxMana(5, 30);
		setMaxMana(10, 25);
		setMaxMana(25, 40);
		setMaxMana(30, 45);
		
		setMaxHealth(1, 13);
		setMaxHealth(5, 15);
		setMaxHealth(10, 17);
		setMaxHealth(25, 19);
		setMaxHealth(30, 20);
		
		setMaxFoodLevel(1, 15);
		setMaxFoodLevel(5, 17);
		setMaxFoodLevel(10, 20);
		setMaxFoodLevel(25, 23);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 0.33f);
		setSpeed(5, 0.34f);
		setSpeed(10, 0.35f);
		setSpeed(25, 0.36f);
		setSpeed(30, 0.37f);
		
		addPower(1, new Blink("Blink", "Quickly teleport to another location!", 60 * 20, new Reagent(10), new Reagent(10)).setMaxDistance(100));
		addPower(1, new Blind("Blind", "Blind nearby enemies!", 60 * 20, new Reagent(10), new Reagent(10)).setRadius(5).setDuration(20 * 10));
		addPower(1, new Vanish("Vanish", "Vanish from your enemies", 120 * 20, new Reagent(10), new Reagent(10)).setDuration(30 * 20));
		
		addAbility(1, new Ability(EffectType.EVASION, "Evade", "Occasionally evade your enemies attacks!", 5));
		addAbility(1, new Ability(EffectType.CRITICAL, "Critical", "Occasionally deal massive damage in hand to hand combat!", 5));
	}
	
}
