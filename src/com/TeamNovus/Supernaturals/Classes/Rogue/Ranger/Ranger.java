package com.TeamNovus.Supernaturals.Classes.Rogue.Ranger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Abilities.BlindArrows;
import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Abilities.PoisonArrows;
import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Abilities.SlowArrows;
import com.TeamNovus.Supernaturals.Classes.Rogue.Ranger.RangerCommon.Powers.Barrage;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Sneak;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Ranger extends SNClass {

	public Ranger(SNClass parentClass) {
		super("Ranger", ChatColor.DARK_GRAY, 30, parentClass);
		
		setMaxMana(1, 1000);
		
		setSpeed(1, 0.24f);
		setSpeed(25, 0.26f);
		
		setMaxHealth(1, 25);
		setMaxHealth(30, 30);
		
		setMaxFoodLevel(1, 40);
		setMaxFoodLevel(30, 45);
		
		// Abilities:
		addAbility(1, new SlowArrows("Slow Arrows", "2% chance to slow your enemies for 10 seconds!", 0, 10 * 20, 2));
		
		addAbility(5, new BlindArrows("Blind Arrows", "3% chance to blind your enemies for 5 seconds!", 0, 5 * 20, 3));
		
		addAbility(20, new PoisonArrows("Poison Arrows", "2% chance to blind your enemies for 5 seconds!", 0, 5 * 20, 2));
		
		addAbility(20, new SlowArrows("Slow Arrows v2", "4% chance to slow your enemies for 10 seconds!", 0, 10 * 20, 4));
		
		addAbility(25, new BlindArrows("Blind Arrows v2", "3% chance to blind your enemies for 15 seconds!", 0, 15 * 20, 3));

		// Powers:
		addPower(5, new Sneak("Sneak", "Hide yourself from your enemies!", 3, 45, 
										new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5))),
										new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5)))));
		
		addPower(10, new Sneak("Vanish", "Dissapear from your enemies!", 3, 60, 
										new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15))),
										new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15)))));
		
		addPower(30, new Barrage("Barrage", "Cause a barrage of arrows to rain down on your enemies!", 0, 45,
										new Reagent(200, new ItemBag(new ItemStack(Material.ARROW, 10), new ItemStack(Material.SULPHUR, 3))),
										new Reagent(200, new ItemBag(new ItemStack(Material.ARROW, 10), new ItemStack(Material.SULPHUR, 3)))));
	}
}
