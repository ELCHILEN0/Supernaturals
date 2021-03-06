package com.TeamNovus.Supernaturals.Custom.Recipe;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.Recipe;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;

public abstract class CustomRecipe {
	protected CustomItemStack result;
	
	public CustomRecipe(CustomItemStack result) {
		this.result = result;
	}
	
	public CustomItemStack getResult() {
		return result;
	}

	public abstract Recipe getBukkitRecipe();
	public abstract boolean matches(Inventory inventory);
	
	public void register() {
		CustomRecipes.registerRecipe(this);
	}
}
