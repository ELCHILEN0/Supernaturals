package com.TeamNovus.Supernaturals.Custom.Enchantment;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;

public abstract class CustomEnchantment extends Enchantment {

	public CustomEnchantment(int id) {
		super(id);

		if(id > 256) {
			throw new IllegalArgumentException("Id must be lower then 256!");
		}
				
		try {
			// Unregister the enchantment if it already exists
			Field byIdField = Enchantment.class.getDeclaredField("byId");
			Field byNameField = Enchantment.class.getDeclaredField("byName");

			byIdField.setAccessible(true);
			byNameField.setAccessible(true);

			@SuppressWarnings("unchecked")
			HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
			@SuppressWarnings("unchecked")
			HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

			if(byId.containsKey(id))
				byId.remove(id);

			if(byName.containsKey(getName()))
				byName.remove(getName());
			
			// Register the enchantment
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
	      	f.setAccessible(true);
	      	f.set(null, true);
	      	
	      	Enchantment.registerEnchantment(this);
	      	Enchantment.stopAcceptingRegistrations();
		} catch (Exception ignored) {  }
	}

	public abstract boolean canEnchantItem(ItemStack arg0);

	public abstract boolean conflictsWith(Enchantment arg0);

	public abstract EnchantmentTarget getItemTarget();

	public abstract int getStartLevel();
	
	public abstract int getMaxLevel();

	public abstract String getEnchantmentName(int level);

	public abstract void onInteract(PlayerInteractEvent event, int level);
	
	public abstract void onInteractEntity(PlayerInteractEntityEvent event, int level);
	
	public abstract void onBlockDamage(BlockDamageEvent event, int level);
	
	public abstract void onBlockBreak(BlockBreakEvent event, int level);
	
	public abstract void onDamageEntity(EntityDamageEntityEvent event, int level);

	@Override
	public String getName() {
		return "Enchantment." + getId();
	}
}
