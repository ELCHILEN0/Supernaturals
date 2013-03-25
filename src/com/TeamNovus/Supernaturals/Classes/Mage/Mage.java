package com.TeamNovus.Supernaturals.Classes.Mage;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Mage.Common.Abilities.LifeLeech;
import com.TeamNovus.Supernaturals.Classes.Mage.Common.Abilities.ManaLeech;
import com.TeamNovus.Supernaturals.Classes.Mage.Common.Powers.Heal;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Mage extends SNClass {

	public Mage(SNClass parentClass) {
		super("Mage", ChatColor.DARK_PURPLE, 30, parentClass);
		
		addPower(1, new Heal("Heal", "Heal youself and nearby players!", 5, 60, 
						new Reagent(10, new ItemBag(new ItemStack(Material.SULPHUR, 3), new ItemStack(Material.GLOWSTONE_DUST, 3))), 
						new Reagent(10, new ItemBag(new ItemStack(Material.SULPHUR, 3), new ItemStack(Material.GLOWSTONE_DUST, 3)))));
		addPower(10, new Heal("Confuse", "Confuse an enemy!", 50, 120, 
						new Reagent(20, new ItemBag(new ItemStack(Material.SULPHUR, 7))), 
						new Reagent(20, new ItemBag(new ItemStack(Material.SULPHUR, 7)))));
		addPower(30, new Heal("Thunderstorm", "Summon the storm of death upon your enemies!", 50, 240, 
				new Reagent(60, new ItemBag(new ItemStack(Material.SULPHUR, 10), new ItemStack(Material.GLOWSTONE_DUST, 10))), 
				new Reagent(60, new ItemBag(new ItemStack(Material.SULPHUR, 10), new ItemStack(Material.GLOWSTONE_DUST, 10)))));
		
		addAbility(5, new ManaLeech("Mana Leech", "Small chance to drain mana when you attack!", 0, 5, 0));
		addAbility(25, new LifeLeech("Life Leech", "Small chance to drain health when you attack!", 0, 5, 0));
		
		setMaxMana(1, 40);
		setMaxMana(5, 45);
		setMaxMana(10, 50);
		setMaxMana(25, 55);
		setMaxMana(30, 60);
		
		setMaxHealth(1, 15);
		setMaxHealth(5, 17);
		setMaxHealth(10, 20);
		setMaxHealth(25, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 25);
		setMaxFoodLevel(5, 25);
		setMaxFoodLevel(10, 25);
		setMaxFoodLevel(25, 25);
		setMaxFoodLevel(30, 25);
		
		setSpeed(1, 0.2f);
		setSpeed(5, 0.21f);
		setSpeed(10, 0.22f);
		setSpeed(25, 0.23f);
		setSpeed(30, 0.24f);
	}
	
}
