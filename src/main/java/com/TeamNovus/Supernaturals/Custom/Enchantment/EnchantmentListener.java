package com.TeamNovus.Supernaturals.Custom.Enchantment;

import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.TeamNovus.Supernaturals.Custom.Inventory.CustomItemStack;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;

public class EnchantmentListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getPlayer().getItemInHand() == null)
			return;
		
		CustomItemStack itemStack = new CustomItemStack(event.getPlayer().getItemInHand());
		
		for(Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
			if(entry.getKey() instanceof CustomEnchantment) {
				CustomEnchantment enchantment = (CustomEnchantment) entry.getKey();
				int level = entry.getValue();

				enchantment.onInteract(event, level);
			}
		}
		
		event.getPlayer().setItemInHand(itemStack.getItemStack());
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		if(event.getPlayer().getItemInHand() == null)
			return;
		
		CustomItemStack itemStack = new CustomItemStack(event.getPlayer().getItemInHand());
		
		for(Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
			if(entry.getKey() instanceof CustomEnchantment) {
				CustomEnchantment enchantment = (CustomEnchantment) entry.getKey();
				int level = entry.getValue();
				
				enchantment.onInteractEntity(event, level);
			}
		}
		
		event.getPlayer().setItemInHand(itemStack.getItemStack());
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent event) {
		if(event.getPlayer().getItemInHand() == null)
			return;
		
		CustomItemStack itemStack = new CustomItemStack(event.getPlayer().getItemInHand());
		
		for(Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
			if(entry.getKey() instanceof CustomEnchantment) {
				CustomEnchantment enchantment = (CustomEnchantment) entry.getKey();
				int level = entry.getValue();
				
				enchantment.onBlockDamage(event, level);
			}
		}
		
		event.getPlayer().setItemInHand(itemStack.getItemStack());
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().getItemInHand() == null)
			return;
		
		CustomItemStack itemStack = new CustomItemStack(event.getPlayer().getItemInHand());
		
		for(Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
			if(entry.getKey() instanceof CustomEnchantment) {
				CustomEnchantment enchantment = (CustomEnchantment) entry.getKey();
				int level = entry.getValue();
				
				enchantment.onBlockBreak(event, level);
			}
		}
		
		event.getPlayer().setItemInHand(itemStack.getItemStack());
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageEntityEvent event) {
		if(event.getEntity() instanceof Player && ((Player) event.getEntity()).getItemInHand() == null)
			return;
		
		CustomItemStack itemStack = new CustomItemStack(((Player) event.getEntity()).getItemInHand());
		
		for(Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
			if(entry.getKey() instanceof CustomEnchantment) {
				CustomEnchantment enchantment = (CustomEnchantment) entry.getKey();
				int level = entry.getValue();
				
				enchantment.onDamageEntity(event, level);
			}
		}
		
		((Player) event.getEntity()).setItemInHand(itemStack.getItemStack());
	}

}
