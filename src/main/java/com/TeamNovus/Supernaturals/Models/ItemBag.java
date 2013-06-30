package com.TeamNovus.Supernaturals.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemBag {
	private List<ItemStack> itemStacks = new ArrayList<ItemStack>();

	public ItemBag(ItemStack... itemStacks) {
		this.itemStacks = Arrays.asList(itemStacks);
	}
	
	public List<ItemStack> getStacks() {
		return itemStacks;
	}
	
	public Boolean has(Player player) {		
		for (ItemStack stack : itemStacks) {
			int total = 0;
			for (ItemStack s : player.getInventory().all(stack.getType()).values()) {
				total += s.getAmount();
			}

			if (total < stack.getAmount())
				return false;
		}
		
		return true;
	}
	
	public void consume(Player player) {
		for (ItemStack stack : itemStacks) {
			player.getInventory().removeItem(stack);
		}
	}
	
	public String toString() {
		String s = "";
		for (ItemStack stack : itemStacks) {
			s += stack.getType().toString() + "x" + stack.getAmount() + " ";
		}
		return s.trim();
	}
	
}
