package com.TeamNovus.Supernaturals.Custom.Recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;

public class CustomRecipes {
	private static List<CustomRecipe> recipes = new ArrayList<CustomRecipe>();

	public static void registerRecipe(CustomRecipe recipe) {
		recipes.add(recipe);
		
		Bukkit.addRecipe(recipe.getBukkitRecipe());
	}
	
	public static CustomRecipe getCustomRecipeFor(Recipe recipe) {
		for(CustomRecipe r : recipes) {
			if(r.sameAs(recipe)) {
				return r;
			}
		}
		
		return null;
	}

}
