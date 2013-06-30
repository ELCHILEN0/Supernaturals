package com.TeamNovus.Supernaturals.Custom.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;
import com.google.common.base.Splitter;

public class CustomRecipeShaped {
	private CustomItemStack result;
	private Material[] materials;
	
	public CustomRecipeShaped(CustomItemStack result) {
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
	
	public void registerRecipe() {
		Bukkit.addRecipe(getBukkitRecipe());
	}
	
}
