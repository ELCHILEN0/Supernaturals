package com.TeamNovus.Supernaturals.Custom.Recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;

public class CustomRecipeShapeless {
	private CustomItemStack result;
	private Material[] materials;
	
	public CustomRecipeShapeless(CustomItemStack result) {
		this.result = result;
	}
	
	public void addIngredients(Material... materials) {
		this.materials = materials;
	}
	
	public CustomItemStack getResult() {
		return result;
	}

	public Material[] getItems() {
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
	
	public void registerRecipe() {
		Bukkit.addRecipe(getBukkitRecipe());
	}
	
}
