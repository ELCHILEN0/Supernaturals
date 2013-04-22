package com.TeamNovus.Supernaturals.Player;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Wand {
	private static HashMap<String, Integer> apptitudes = new HashMap<String, Integer>();
	
	public static void addRecipies() {
		ShapedRecipe basicWand = new ShapedRecipe(Wand.createWand("Wand of Apprentice", "I", 1));
		basicWand.shape(" R ", " S ", " S ");
		basicWand.setIngredient('R', Material.REDSTONE);
		basicWand.setIngredient('S', Material.STICK);
		
		ShapedRecipe adeptWand = new ShapedRecipe(Wand.createWand("Wand of Adept", "II", 2));
		adeptWand.shape(" G ", "S", "S");
		adeptWand.setIngredient('G', Material.GOLD_INGOT);
		adeptWand.setIngredient('S', Material.STICK);
		
		ShapedRecipe masterWand = new ShapedRecipe(Wand.createWand("Wand of Masters", "II", 3));
		masterWand.shape("DDD", "GBG", "GSG");
		masterWand.setIngredient('D', Material.DIAMOND);
		masterWand.setIngredient('G', Material.GOLD_INGOT);
		masterWand.setIngredient('B', Material.BLAZE_ROD);
		
		Bukkit.addRecipe(basicWand);
		Bukkit.addRecipe(adeptWand);
		Bukkit.addRecipe(masterWand);
	}
	
	public static ItemStack createWand(String name, String apptitudeKey, Integer apptitude) {
		ItemStack wand = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = wand.getItemMeta();
				
		meta.setDisplayName(ChatColor.AQUA + name);
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Aptitude " + apptitudeKey);
		meta.setLore(lore);
		
		wand.setItemMeta(meta);

		apptitudes.put(apptitudeKey, apptitude);
		
		return wand;
	}
	
	public static boolean isWand(ItemStack i) {
		for(String s : i.getItemMeta().getLore()) {
			if(s.startsWith(ChatColor.GRAY + "Aptitude ") && s.split(ChatColor.GRAY + "Aptitude ").length == 2) {
				return true;
			}
		}
	
		return false;
	}

	public static int getApptitude(ItemStack i) {
		for(String s : i.getItemMeta().getLore()) {
			if(s.startsWith(ChatColor.GRAY + "Aptitude ") && s.split(ChatColor.GRAY + "Aptitude ").length == 2) {
				return apptitudes.get(s.split(ChatColor.GRAY + "Aptitude ")[1]);
			}
		}
		
		return 0;
	}
	
}
