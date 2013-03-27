package com.TeamNovus.Supernaturals.Classes.Rogue;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Abilities.Blind;
import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Abilities.PoisonArrows;
import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers.Leap;
import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers.Sneak;
import com.TeamNovus.Supernaturals.Classes.Rogue.Common.Powers.Vanish;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Rogue extends SNClass {
	
	public Rogue(SNClass parentClass) {
		super("Rogue", ChatColor.DARK_GREEN, 30, parentClass);
		
		addPower(1, new Leap("Leap", "Leap away from danger!", 15, 10, 
						new Reagent(5, new ItemBag(new ItemStack(Material.SULPHUR, 2), new ItemStack(Material.STRING, 2))),
						new Reagent(5, new ItemBag(new ItemStack(Material.SULPHUR, 2), new ItemStack(Material.STRING, 2)))));
		addPower(10, new Sneak("Sneak", "Hide from your enemies!", 60, 120, 
						new Reagent(5, new ItemBag(new ItemStack(Material.SULPHUR, 10))),
						new Reagent(5, new ItemBag(new ItemStack(Material.SULPHUR, 10)))));
		addPower(30, new Vanish("Vansh", "Completely vanish from your enemies!", 60, 120, 
						new Reagent(5, new ItemBag(new ItemStack(Material.SULPHUR, 25))),
						new Reagent(5, new ItemBag(new ItemStack(Material.SULPHUR, 25)))));
		
		addAbility(5, new Blind("Blind", "Small chance to blind attackers!", 0, 10, 2));
		addAbility(25, new PoisonArrows("Poison Arrows", "Your arrows occasionally turn poisonous!", 0, 10, 2));
		
		setMaxMana(1, 30);
		setMaxMana(5, 35);
		setMaxMana(10, 40);
		setMaxMana(25, 45);
		setMaxMana(30, 50);
		
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
		
		setSpeed(1, 0.5f);
		setSpeed(5, 0.31f);
		setSpeed(10, 0.32f);
		setSpeed(25, 0.33f);
		setSpeed(30, 0.34f);
		
	}

}
