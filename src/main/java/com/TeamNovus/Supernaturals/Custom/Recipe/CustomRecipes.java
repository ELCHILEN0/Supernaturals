package com.TeamNovus.Supernaturals.Custom.Recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.CraftingInventory;

public class CustomRecipes {
	private static List<CustomRecipe> recipes = new ArrayList<CustomRecipe>();

	public static void registerRecipe(CustomRecipe recipe) {
		recipes.add(recipe);

		Bukkit.addRecipe(recipe.getBukkitRecipe());
	}
	
	public static CustomRecipe getRecipeFor(CraftingInventory inventory) {
		for(CustomRecipe recipe : recipes) {
			if(recipe.matches(inventory)) {
				return recipe;
			}
		}
		
		return null;
	}

}
