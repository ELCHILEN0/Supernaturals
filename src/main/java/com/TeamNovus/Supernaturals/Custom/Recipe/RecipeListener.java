package com.TeamNovus.Supernaturals.Custom.Recipe;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

public class RecipeListener implements Listener {

	public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) {
		event.getInventory().setResult(CustomRecipes.getCustomRecipeFor(event.getRecipe()).getResult().getItemStack());
	}

}
