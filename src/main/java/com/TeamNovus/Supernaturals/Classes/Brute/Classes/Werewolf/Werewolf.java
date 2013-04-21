package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers.Boost;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Abilities.Claws;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Abilities.NightRavage;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Powers.Pounce;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Powers.Quicken;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Werewolf extends SNClass {

	public Werewolf() {
		super("Werewolf", ChatColor.DARK_GRAY, 30);
		
		addAbility(5, new Claws("Claws", "All your attacks deal a small damage boost!", 0));
		addAbility(25, new NightRavage("Night Ravage", "You deal extra damage at night but more during the daytime!", 0));
		
		addPower(5, new Boost("Sneak", "Hide from enemies by sneaking!", 60, 30, new Reagent(), new Reagent()));
		addPower(15, new Quicken("Quicken", "Gain a quick speed boost temporarily!", 60, 30, new Reagent(), new Reagent()));
		addPower(25, new Pounce("Pounce", "Supprise your enemies by pouncing upon them!", 60, 100, new Reagent(), new Reagent()));
		
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
