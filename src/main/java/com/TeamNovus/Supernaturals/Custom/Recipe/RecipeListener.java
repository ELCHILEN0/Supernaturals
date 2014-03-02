package com.TeamNovus.Supernaturals.Custom.Recipe;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

public class RecipeListener implements Listener {

	@EventHandler
	public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) {
		CustomRecipe recipe = CustomRecipes.getRecipeFor(event.getInventory());
		
		if(recipe != null) {	
			event.getInventory().setResult(recipe.getResult().getItemStack());
		}
	}

}
