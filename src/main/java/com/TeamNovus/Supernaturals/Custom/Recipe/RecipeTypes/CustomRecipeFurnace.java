package com.TeamNovus.Supernaturals.Custom.Recipe.RecipeTypes;

import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;
import com.TeamNovus.Supernaturals.Custom.Recipe.CustomRecipe;

public class CustomRecipeFurnace extends CustomRecipe {
	private Material material;
	
	public CustomRecipeFurnace(CustomItemStack result) {
		super(result);
	}
	
	public Recipe getBukkitRecipe() {
		FurnaceRecipe recipe = new FurnaceRecipe(result.getItemStack(), material);
		
		return recipe;
	}
	
	@Override
	public boolean sameAs(Recipe recipe) {
		if(recipe instanceof FurnaceRecipe) {
			FurnaceRecipe r = (FurnaceRecipe) recipe;
			
			return r.getInput().getType() == material;
		}
		
		return false;
	}
	
}
