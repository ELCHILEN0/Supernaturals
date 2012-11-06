package com.TeamNovus.SupernaturalRaces.Race.Demon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Models.Reagent;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class ShockHeal implements Spell {

	@Override
	public String name() {
		return "ShockHeal";
	}

	@Override
	public String info() {
		return "Instantly give yourself max health!";
	}

	@Override
	public Material binding() {
		return Material.GLOWSTONE_DUST;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, new ItemStack(Material.GLOWSTONE_DUST, 1), 100);
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		sender.getWorld().strikeLightning(sender.getLocation());
		sender.setHealth(sender.getMaxHealth());
		sender.sendMessage(ChatColor.GOLD + "You have been healed by lightning!");
		return true;
	}

}
