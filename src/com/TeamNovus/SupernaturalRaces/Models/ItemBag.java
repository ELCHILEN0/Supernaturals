package com.TeamNovus.SupernaturalRaces.Models;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemBag {
	private ItemStack[] itemStacks;

	public ItemBag(ItemStack... itemStacks) {
		this.itemStacks = itemStacks;
	}
	
	public Boolean has(Player player) {
		for(ItemStack stack : itemStacks) {
			if(!(player.getInventory().contains(stack))) {
				return false;
			}
		}
		return true;
	}
	
	public void consume(Player player) {
		player.getInventory().removeItem(itemStacks);
	}
	
}
