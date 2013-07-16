package com.TeamNovus.Supernaturals.Custom.Recipe.RecipeTypes;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;
import com.TeamNovus.Supernaturals.Custom.Recipe.CustomRecipe;

public class CustomRecipeShapeless extends CustomRecipe {
	private Material[] materials;

	public CustomRecipeShapeless(CustomItemStack result) {
		super(result);
	}
	
	public void addIngredients(Material... materials) {
		this.materials = materials;
	}

	public Material[] getIngredients() {
		return materials;
	}
	
	public Recipe getBukkitRecipe() {
		ShapelessRecipe recipe = new ShapelessRecipe(result.getItemStack());
		
		for(Material material : materials) {
			if(material != null) {
				recipe.addIngredient(material, -1);
			}
		}
		
		return recipe;
	}
	
	@Override
	public boolean sameAs(Recipe recipe) {
		if(recipe instanceof ShapelessRecipe) {
			ShapelessRecipe r = (ShapelessRecipe) recipe;
			
			return r.getIngredientList().equals(((ShapelessRecipe) getBukkitRecipe()).getIngredientList());
		}
		
		return false;
	}
	
}
