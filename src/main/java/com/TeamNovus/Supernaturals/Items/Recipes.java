package com.TeamNovus.Supernaturals.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;
import com.TeamNovus.Supernaturals.Custom.Recipe.CustomRecipeShaped;

public class Recipes {

	public static void register() {
		// Apprentice Wand:
		CustomItemStack apprenticeWand = new CustomItemStack(Material.BLAZE_ROD, 1);
		apprenticeWand.setDisplayName(ChatColor.AQUA + "Wand of Apprentice");
		apprenticeWand.addUnsafeEnchantment(SNEnchantment.APTITUDE, 1);

		CustomRecipeShaped apprenticeRecipe = new CustomRecipeShaped(apprenticeWand);
		apprenticeRecipe.addIngredients(null, Material.REDSTONE, null,
										null, Material.STICK, null,
										null, Material.STICK, null);
		apprenticeRecipe.registerRecipe();

		// Adept Wand:
		CustomItemStack adeptWand = new CustomItemStack(Material.BLAZE_ROD, 1);
		adeptWand.setDisplayName(ChatColor.AQUA + "Wand of Adept");
		adeptWand.addUnsafeEnchantment(SNEnchantment.APTITUDE, 2);

		CustomRecipeShaped adeptRecipe = new CustomRecipeShaped(adeptWand);
		adeptRecipe.addIngredients(Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT,
								   null, Material.BLAZE_ROD, null,
								   null, Material.BLAZE_ROD, null);
		adeptRecipe.registerRecipe();

		// Master Wand:
		CustomItemStack masterWand = new CustomItemStack(Material.BLAZE_ROD, 1);
		masterWand.setDisplayName(ChatColor.AQUA + "Wand of Masters");
		masterWand.addUnsafeEnchantment(SNEnchantment.APTITUDE, 3);

		CustomRecipeShaped masterRecipe = new CustomRecipeShaped(adeptWand);
		masterRecipe.addIngredients(Material.DIAMOND, Material.DIAMOND, Material.DIAMOND,
									Material.GOLD_INGOT, Material.BLAZE_ROD, Material.GOLD_INGOT,
									Material.GOLD_INGOT, Material.BLAZE_ROD, Material.GOLD_INGOT);
		masterRecipe.registerRecipe();		
	}

}
