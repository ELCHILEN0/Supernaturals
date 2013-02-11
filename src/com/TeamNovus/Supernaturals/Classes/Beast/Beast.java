package com.TeamNovus.Supernaturals.Classes.Beast;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Rogue.Assassin.Assassin;
import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.Ranger;
import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Abilities.BlindArrows;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Leap;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Sneak;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Vanish;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Beast extends SNClass {

	public Beast(SNClass parentClass) {
		super("Beast", ChatColor.DARK_GREEN, 30, parentClass);
		
		addJoinableClass(1, new Ranger(this));
		addJoinableClass(25, new Assassin(this));
		
		setMaxMana(1, 750);
		
		setSpeed(1, 0.23f);
		setSpeed(25, 0.25f);
		
		setMaxHealth(1, 23);
		setMaxHealth(30, 25);
		
		setMaxFoodLevel(1, 30);
		setMaxFoodLevel(30, 40);
		
		// Abilities:
		addAbility(20, new BlindArrows("BlindArrows", "Blind your foes!", 0, 20 * 20, 100));
		
		// Powers:
		addPower(5, new Sneak("Sneak", "Hide yourself from your enemies!", 3, 300, 
										new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5))),
										new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5)))));
		
		addPower(10, new Vanish("Vanish", "Dissapear from your enemies!", 3, 300, 
										new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15))),
										new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15)))));
		
		addPower(1, new Leap("Leap", "Leap over immense gaps!", 0, 30,
				new Reagent(75, new ItemBag(new ItemStack(Material.FEATHER, 2))),
				new Reagent(75, new ItemBag(new ItemStack(Material.FEATHER, 2)))));
	}
}
