package com.TeamNovus.Supernaturals.Classes.Rogue.Assassin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Classes.Rogue.Assassin.AssassinCommon.Abilities.PoisonSword;
import com.TeamNovus.Supernaturals.Classes.Rogue.Assassin.AssassinCommon.Powers.Backstab;
import com.TeamNovus.Supernaturals.Classes.Rogue.Assassin.AssassinCommon.Powers.Strike;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Leap;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Sneak;
import com.TeamNovus.Supernaturals.Classes.Rogue.RogueCommon.Powers.Vanish;
import com.TeamNovus.Supernaturals.Models.ItemBag;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Assassin extends SNClass {

	public Assassin(SNClass parentClass) {
		super("Assassin", ChatColor.DARK_GRAY, 30, parentClass);
		
		setSpeed(1, 0.3f);

		setMaxHealth(1, 17);

		setMaxFoodLevel(1, 40);
		
		// Abilities:
		addAbility(5, new PoisonSword("Poison Sword", "Your swords have a 5% chance to poison enemies for 10 seconds!", 0, 10, 5));

		addAbility(20, new PoisonSword("Poison Sword v2", "Your swords have a 15% chance to poison enemies for 15 seconds!", 0, 15, 15));

		
		// Powers:
		addPower(1, new Leap("Leap", "Leap over immense gaps!", 0, 30,
												new Reagent(75, new ItemBag(new ItemStack(Material.FEATHER, 2))),
												new Reagent(75, new ItemBag(new ItemStack(Material.FEATHER, 2)))));
		
		addPower(5, new Sneak("Sneak", "Hide yourself from your enemies!", 3, 60, 
												new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5))),
												new Reagent(50, new ItemBag(new ItemStack(Material.SULPHUR, 5)))));

		addPower(10, new Vanish("Vanish", "Dissapear from your enemies!", 3, 60, 
												new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15))),
												new Reagent(150, new ItemBag(new ItemStack(Material.SULPHUR, 15)))));
		
		addPower(20, new Strike("Strike", "Strike and teleport to your enemies instantly!", 0, 60,
												new Reagent(200, new ItemBag(new ItemStack(Material.FEATHER, 2), new ItemStack(Material.SULPHUR, 2))),
												new Reagent(200, new ItemBag(new ItemStack(Material.FEATHER, 2), new ItemStack(Material.SULPHUR, 2)))));
		
		addPower(30, new Backstab("Backstab", "A close range mele attack that deals MASSIVE damage!", 0, 300,
												new Reagent(250, new ItemBag(new ItemStack(Material.SULPHUR, 20))),
												new Reagent(250, new ItemBag(new ItemStack(Material.SULPHUR, 20)))));
		}

}
