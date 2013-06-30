package com.TeamNovus.Supernaturals.Custom.Enchantment;

import java.lang.reflect.Field;

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
			throw new IllegalArgumentException("Id must bebe lower then 256!");
		}
		
		try {
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
