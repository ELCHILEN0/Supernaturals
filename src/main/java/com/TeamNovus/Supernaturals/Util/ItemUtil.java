package com.TeamNovus.Supernaturals.Util;

import net.minecraft.server.v1_5_R2.NBTTagCompound;
import net.minecraft.server.v1_5_R2.NBTTagList;

import org.bukkit.craftbukkit.v1_5_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {

	public static boolean hasGlow(ItemStack item) {
		net.minecraft.server.v1_5_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

		if (!(nmsStack.hasTag())) {
			return false;
		}

		NBTTagCompound tag = null;
		tag = nmsStack.getTag();

		return tag.hasKey("ench");
	}

	public static ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_5_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = nmsStack.tag;

		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.tag = compound;
		}

		compound.set("ench", new NBTTagList());

		return CraftItemStack.asCraftMirror(nmsStack);
	}

	public static ItemStack removeGlow(ItemStack item) {
		net.minecraft.server.v1_5_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

		if (!(nmsStack.hasTag())) {
			return item;
		}

		NBTTagCompound tag = null;
		tag = nmsStack.getTag();
		tag.set("ench", null);
		nmsStack.setTag(tag);

		return CraftItemStack.asCraftMirror(nmsStack);
	}
}
