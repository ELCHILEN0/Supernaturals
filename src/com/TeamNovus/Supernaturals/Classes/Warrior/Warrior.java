package com.TeamNovus.Supernaturals.Classes.Warrior;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Abilities.DoubleStrike;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Abilities.PowerStrike;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers.Shell;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers.Strike;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers.Tornado;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Warrior extends SNClass {

	public Warrior(SNClass parentClass) {
		super("Warrior", ChatColor.GOLD, 30, parentClass);
		
		addPower(1, new Strike("Strike", "Instantly damage a player!", 50, 4, 0, new Reagent(), new Reagent()));
		addPower(10, new Shell("Shell", "Take reduced damage!", 60, 0, new Reagent(), new Reagent()));
		addPower(30, new Tornado("Tornado", "Toss nearby eneimes into the air!", 20, 0, new Reagent(), new Reagent()));
		
		addAbility(5, new DoubleStrike("Double Strike", "Your attacks have a small chance to deal double damage!", 5, 0));
		addAbility(10, new PowerStrike("Power Strike", "Your attacks have a small chance to deal tripple damage!", 2, 0));
		
		setMaxMana(1, 20);
		setMaxMana(5, 25);
		setMaxMana(10, 30);
		setMaxMana(25, 35);
		setMaxMana(30, 40);
		
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
		
		setSpeed(1, 1.0f);
		setSpeed(5, 1.0f);
		setSpeed(10, 1.0f);
		setSpeed(25, 1.0f);
		setSpeed(30, 1.0f);
	}
	
}
