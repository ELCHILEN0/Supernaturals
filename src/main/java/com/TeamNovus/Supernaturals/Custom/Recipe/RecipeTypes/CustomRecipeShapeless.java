package com.TeamNovus.Supernaturals.Custom.Recipe.RecipeTypes;

import org.bukkit.Material;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
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
				recipe.addIngredient(material);
			}
		}
		
		return recipe;
	}
	
	@Override
	public boolean matches(Inventory inventory) {
		if(inventory instanceof CraftingInventory) {
			CraftingInventory i = (CraftingInventory) inventory;

			if(i.getRecipe() instanceof ShapelessRecipe) {
				ShapelessRecipe r = (ShapelessRecipe) i.getRecipe();
				ShapelessRecipe recipe = (ShapelessRecipe) getBukkitRecipe();
				
				if(r.getIngredientList().equals(recipe.getIngredientList())) {
					return true;
				}				
			}
		}
		
		return false;
	}
	
}
