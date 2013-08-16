package com.TeamNovus.Supernaturals.Custom.Recipe.RecipeTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;
import com.TeamNovus.Supernaturals.Custom.Recipe.CustomRecipe;
import com.google.common.base.Splitter;

public class CustomRecipeShaped extends CustomRecipe {
	private Material[] materials;

	public CustomRecipeShaped(CustomItemStack result) {
		super(result);
	}
	
	public void addIngredients(Material... materials) {
		this.materials = materials;
	}

	public Material[] getIngredients() {
		return materials;
	}

	public Recipe getBukkitRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(result.getItemStack());

		char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
		Map<Material, Character> charMapping = new HashMap<Material, Character>();
				
		String s = "";
		for (int i = 0; i < 9; i++) {
			Material material = null;
			try {
				material = materials[i];
			} catch(Exception ignored) { }
			
			if(material == null) {
				s += " ";
			} else {
				if(!(charMapping.containsKey(material))) {
					for(Character c : chars) {
						if(!(charMapping.containsValue(c))) {
							charMapping.put(material, c);
							break;
						}
					}
				}
				
				s += charMapping.get(material);
			}
		}
		
		List<String> shape = new ArrayList<String>();
		Iterator<String> split = Splitter.fixedLength(3).limit(3).split(s).iterator();
		
		while(split.hasNext()) {			
			shape.add(split.next());
		}
		
		recipe.shape(shape.get(0), shape.get(1), shape.get(2));
		for(Entry<Material, Character> entry : charMapping.entrySet()) {
			recipe.setIngredient(entry.getValue(), entry.getKey());
		}
		
		return recipe;
	}
	
	@Override
	public boolean matches(Inventory inventory) {
		if(inventory instanceof CraftingInventory) {
			ItemStack[] items = ((CraftingInventory) inventory).getMatrix();
			
			for (int i = 0; i < 9; i++) {
				Material table = items[i].getType();
				Material recipe = materials[i] == null ? Material.AIR : materials[i];

				if(!(table.equals(recipe))) {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
}
