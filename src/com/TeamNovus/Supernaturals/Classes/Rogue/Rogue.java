package com.TeamNovus.Supernaturals.Classes.Rogue;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Rogue.Assassin.Assassin;
import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.Ranger;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Sneak;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Rogue extends SNClass {

	public Rogue(String name, ChatColor color, Integer maxLevel) {
		super(name, color, maxLevel);
		
		addJoinableClass(25, new Ranger("Ranger", getColor(), 30));
		addJoinableClass(25, new Assassin("Ranger", getColor(), 30));
		
		setSpeed(0, 0.23f);
		setSpeed(25, 0.25f);
		
		setMaxHealth(0, 25);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(0, 40);
		setMaxFoodLevel(30, 60);
		
		addPower(5, new Sneak("Sneak", "Hide yourself from your enemies!", 3, 300, 
										new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5))),
										new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5)))));
		
		addPower(10, new Sneak("Vanish", "Dissapear from your enemies!", 3, 300, 
										new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15))),
										new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15)))));
	}

}
