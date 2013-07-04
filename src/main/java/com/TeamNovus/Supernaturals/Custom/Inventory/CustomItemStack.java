package com.TeamNovus.Supernaturals.Custom.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.server.v1_6_R1.NBTTagCompound;
import net.minecraft.server.v1_6_R1.NBTTagList;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import com.TeamNovus.Supernaturals.Custom.Enchantment.CustomEnchantment;

public class CustomItemStack {
	private ItemStack handle;
	
	public CustomItemStack(Material material) {
		handle = new ItemStack(material);
	}
	
	public CustomItemStack(Material material, int amount) {
		handle = new ItemStack(material, amount);
	}
	
	public CustomItemStack(Material material, int amount, short damage) {
		handle = new ItemStack(material, amount, damage);
	}
	
	public CustomItemStack(int id) {
		handle = new ItemStack(id);
	}
	
	public CustomItemStack(int id, int amount) {
		handle = new ItemStack(id, amount);
	}
	
	public CustomItemStack(int id, int amount, short damage) {
		handle = new ItemStack(id, amount, damage);
	}
	
	public CustomItemStack(ItemStack i) {
		handle = i.clone();
	}
	
	/**
	 * Get the Material of the ItemStack.
	 * 
	 * @return The Material of the ItemStack.
	 */
	public Material getType() {
		return handle.getType();
	}
	
	/**
	 * Get the Material ID of the ItemStack.
	 * 
	 * @return The Material ID.
	 */
	public int getTypeId() {
		return handle.getTypeId();
	}
	
	/**
	 * Get the MaterialData of the ItemStack.
	 * 
	 * @return The MaterialData.
	 */
	public MaterialData getData() {
		return handle.getData();
	}
	
	/**
	 * Get the durability of the ItemStack.
	 * 
	 * @return The durability of the ItemStack.
	 */
	public short getDurability() {
		return handle.getDurability();
	}
	
	/**
	 * Get the maxStackSize of the ItemStack.
	 * 
	 * @return The maxStackSize of the ItemStack.
	 */
	public int getMaxStackSize() {
		return handle.getMaxStackSize();
	}
	
	/**
	 * Get the ItemStack's ItemMeta.
	 * 
	 * @return The ItemStack's ItemMeta.
	 */
	public ItemMeta getItemMeta() {
		return handle.getItemMeta();
	}
	
	/**
	 * Set the ItemStack's ItemMeta.
	 * 
	 * @param itemMeta - The new ItemMeta.
	 */
	public void setItemMeta(ItemMeta itemMeta) {
		handle.setItemMeta(itemMeta);
	}
	
	/**
	 * Set the displayName of the ItemStack.
	 * 
	 * @param name - The new displayName of the ItemStack.
	 */
	public void setDisplayName(String name) {
		ItemMeta meta = getItemMeta();
		meta.setDisplayName(name);
		
		setItemMeta(meta);
	}
	
	/**
	 * Get the displayName of the ItemStack.
	 * 
	 * @return The displayName of the ItemStack.
	 */
	public String getDisplayName() {
		return getItemMeta().getDisplayName();
	}
	
	/**
	 * Check if a displayName is set for the ItemStack.
	 * 
	 * @return True if the ItemStack has a custom displayName, false otherwise.
	 */
	public boolean hasDisplayName() {
		return getItemMeta().hasDisplayName();
	}
	
	/**
	 * Add Lore to the ItemStack.
	 * 
	 * @param lore - A list of new lore to add.
	 */
	public void addLore(List<String> lore) {
		ItemMeta meta = getItemMeta();
		List<String> l = hasLore() ? meta.getLore() : new ArrayList<String>();
		
		if(lore != null) {
			l.addAll(lore);
		}
		
		meta.setLore(l);
		
		setItemMeta(meta);
	}
	
	/**
	 * Set the ItemStack's Lore.
	 * 
	 * @param lore - The ItemStack's new lore.
	 */
	public void setLore(List<String> lore) {
		ItemMeta meta = getItemMeta();
		meta.setLore(lore);
		
		setItemMeta(meta);
	}
	
	/**
	 * Add Lore to the ItemStack.
	 * 
	 * @param lore - A list of new lore to add.
	 */
	public void addLore(String... lore) {
		addLore(Arrays.asList(lore));
	}
	
	/**
	 * Set the ItemStack's Lore.
	 * 
	 * @param lore - The ItemStack's new lore.
	 */
	public void setLore(String... lore) {
		setLore(Arrays.asList(lore));
	}
	
	/**
	 * Check if the ItemStack has lore set.
	 * 
	 * @return True if the ItemStack has lore, false otherwise.
	 */
	public boolean hasLore() {
		return getItemMeta().hasLore();
	}
	
	/**
	 * Get the ItemStack's lore.
	 * 
	 * @return The ItemStacks lore as a List.
	 */
	public List<String> getLore() {
		return getItemMeta().getLore();
	}
	
	/**
	 * Add an enchantment to the ItemStack disregarding level.
	 * 
	 * @param enchantment - The enchantment to add.
	 * @param level - The level of the enchantment.
	 */
	public void addUnsafeEnchantment(Enchantment enchantment, int level) {
		handle.addUnsafeEnchantment(enchantment, level);
		
		if(enchantment instanceof CustomEnchantment) {
			CustomEnchantment e = (CustomEnchantment) enchantment;
			
			if(e.getEnchantmentName(level) != null) {
				addLore(e.getEnchantmentName(level));
			}
		}
		
	}
	
	/**
	 * Add multiple enchantments to the ItemStack disregarding level.
	 * 
	 * @param enchantments - A map with the enchantments to add.
	 */
	public void addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
		for(Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
			addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());
		}
	}
	
	/**
	 * Add an enchantment to the ItemStack.
	 * 
	 * @param enchantment - The enchantment to add.
	 * @param level
	 */
	public void addEnchantment(Enchantment enchantment, int level) {
		handle.addEnchantment(enchantment, level);
		
		if(enchantment instanceof CustomEnchantment) {
			CustomEnchantment e = (CustomEnchantment) enchantment;
			
			if(e.getEnchantmentName(level) != null) {
				addLore(e.getEnchantmentName(level));
			}
		}
	}
	
	/**
	 * Add an enchantment to the ItemStack.
	 * 
	 * @param enchantments - A map with the enchantments to add.
	 */
	public void addEnchantments(Map<Enchantment, Integer> enchantments) {
		for(Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
			addEnchantment(enchantment.getKey(), enchantment.getValue());
		}
	}
	
	/**
	 * Remove an enchantment from the ItemStack.
	 * 
	 * @param enchantment - The enchantment to remove.
	 */
	public void removeEnchantment(Enchantment enchantment) {
		handle.removeEnchantment(enchantment);
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return handle.getEnchantments();
	}
	
	/**
	 * Check if the ItemStack contains a specific enchantment.
	 * 
	 * @param enchantment - The enchantment to check.
	 * @return true if the enchantment is present, false otherwise.
	 */
	public boolean hasEnchantment(Enchantment enchantment) {
		return handle.containsEnchantment(enchantment);
	}
	
	/**
	 * Gets the level of an enchantment on the ItemStack.
	 * 
	 * @param enchantment - The enchantment to check.
	 * @return The enchantments level or 0.
	 */
	public int getEnchantmentLevel(Enchantment enchantment) {
		return handle.getEnchantmentLevel(enchantment);
	}
	
	/**
	 * Check if the ItemStack contains any enchantments.
	 * 
	 * @return true if enchantments are present, false otherwise.
	 */
	public boolean hasEnchantments() {
		return getItemMeta().hasEnchants();
	}
	
	/**
	 * Add a visible glow to the ItemStack.
	 */
	public void addGlow() {
		net.minecraft.server.v1_6_R1.ItemStack nmsStack = getNMSStack();
		NBTTagCompound compound = nmsStack.tag;

		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.tag = compound;
		}

		compound.set("ench", new NBTTagList());
		
		handle = CraftItemStack.asCraftMirror(nmsStack);
	}
	
	/**
	 * Remove a glow from the ItemStack.
	 */
	public void removeGlow() {
		net.minecraft.server.v1_6_R1.ItemStack nmsStack = getNMSStack();
		NBTTagCompound compound = nmsStack.tag;

		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.tag = compound;
		}

		compound.set("ench", null);
		
		handle = CraftItemStack.asCraftMirror(nmsStack);
	}
	
	/**
	 * Check if the ItemStack has a visible glow.
	 * 
	 * @return True if the glow is present, false otherwise.
	 */
	public boolean hasGlow() {
		net.minecraft.server.v1_6_R1.ItemStack nmsStack = getNMSStack();
		NBTTagCompound compound = nmsStack.tag;

		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.tag = compound;
		}

		return compound.hasKey("ench");
	}
	
	/**
	 * Get the NMS instance of the ItemStack.
	 * 
	 * @return The NMS ItemStack.
	 */
	public net.minecraft.server.v1_6_R1.ItemStack getNMSStack() {
		return CraftItemStack.asNMSCopy(handle);
	}
	
	/**
	 * Get the CraftItemStack instance of the ItemStack.
	 * 
	 * @return The CraftItemStack.
	 */
	public CraftItemStack getCraftItemStack() {
		return CraftItemStack.asCraftMirror(getNMSStack());
	}
	
	/**
	 * Get the ItemStack instance.
	 * 
	 * @return The ItemStack.
	 */
	public ItemStack getItemStack() {
		return handle.clone();
	}

}
