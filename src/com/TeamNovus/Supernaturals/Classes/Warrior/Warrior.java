package com.TeamNovus.Supernaturals.Classes.Warrior;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Abilities.DoubleStrike;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Abilities.PowerStrike;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers.Shell;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers.Strike;
import com.TeamNovus.Supernaturals.Classes.Warrior.Common.Powers.Tornado;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Warrior extends SNClass {

	public Warrior(SNClass parentClass) {
		super("Warrior", ChatColor.GOLD, 30, parentClass);
		
		addPower(1, new Strike("Strike", "Instantly damage a player!", 50, 4, 20, 
						new Reagent(5, new ItemBag(new ItemStack(Material.STONE_SWORD, 1), new ItemStack(Material.SULPHUR, 1))),
						new Reagent(5, new ItemBag(new ItemStack(Material.STONE_SWORD, 1), new ItemStack(Material.SULPHUR, 1)))));
		addPower(10, new Shell("Shell", "Take reduced damage!", 60, 120, 
						new Reagent(20, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 15))),
						new Reagent(20, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 15)))));
		addPower(30, new Tornado("Tornado", "Toss nearby eneimes into the air!", 20, 240, 
				new Reagent(30, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 15), new ItemStack(Material.SULPHUR, 15))),
				new Reagent(30, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 15), new ItemStack(Material.SULPHUR, 15)))));
		
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
		
		setSpeed(1, 0.2f);
		setSpeed(5, 0.2f);
		setSpeed(10, 0.2f);
		setSpeed(25, 0.2f);
		setSpeed(30, 0.2f);
	}
	
}
