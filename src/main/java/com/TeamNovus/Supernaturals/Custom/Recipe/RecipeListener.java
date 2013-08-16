package com.TeamNovus.Supernaturals.Custom.Recipe;

import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import com.TeamNovus.Supernaturals.Custom.Enchantment.CustomEnchantment;

public class RecipeListener implements Listener {

	@EventHandler
	public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) {
		CustomRecipe recipe = CustomRecipes.getRecipeFor(event.getInventory());
		
		if(recipe != null) {	
			event.getInventory().setResult(recipe.getResult().getItemStack());
			
			for(Entry<Enchantment, Integer> entry : recipe.getResult().getItemStack().getEnchantments().entrySet()) {
				System.out.println(entry.getKey() instanceof CustomEnchantment);
			}
			
			for(Entry<Enchantment, Integer> entry : event.getInventory().getResult().getEnchantments().entrySet()) {
				System.out.println(entry.getKey() instanceof CustomEnchantment);
			}
		}
	}

}
