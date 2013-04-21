package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Abilities.NightDamage;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Abilities.VampiricBite;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers.Boost;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers.Leech;
import com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Powers.Vanish;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Vampire extends SNClass {

	public Vampire(SNClass parentClass) {
		super("Vampire", ChatColor.GRAY, 30, parentClass);
		
		addAbility(5, new NightDamage("Night Damage", "Your attacks deal extra damage during the night!", 2));
		addAbility(25, new VampiricBite("Vampiric Bite", "Your attacks occasionally cause your enemy to bleed!", 2, 30, 2));
		
		addPower(5, new Boost("Boost", "Temporarily boost your speed!", 60, 30, new Reagent(), new Reagent()));
		addPower(10, new Vanish("Vanish", "Vanish from your enemies!", 60, 100, 30, new Reagent(), new Reagent()));
		addPower(25, new Leech("Leech", "Leech life from any enemies!", 120, 100, new Reagent(), new Reagent()));
		
		setMaxHealth(1, 17);
		setMaxHealth(5, 19);
		setMaxHealth(10, 20);
		setMaxHealth(20, 22);
		setMaxHealth(30, 25);
		
		setMaxMana(1, 30);
		setMaxMana(5, 35);
		setMaxMana(10, 40);
		setMaxMana(20, 45);
		setMaxMana(30, 50);
		
		setMaxFoodLevel(1, 10);
		setMaxFoodLevel(5, 12);
		setMaxFoodLevel(20, 13);
		setMaxFoodLevel(30, 15);
		
		setSpeed(1, .25f);
		setSpeed(5, .26f);
		setSpeed(20, .28f);
		setSpeed(30, .30f);		
	}

}
