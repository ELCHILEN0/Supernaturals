package com.TeamNovus.SupernaturalRaces.Race.Demon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class ShockHeal implements Spell {

	@Override
	public String name() {
		return "ShockHeal";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 100, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 2)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		sender.getWorld().strikeLightning(sender.getLocation());
		sender.setHealth(sender.getHealth() + (int) (sender.getMaxHealth()*.5));
		sender.sendMessage(ChatColor.GOLD + "You have been healed by lightning!");
		return true;
	}

}
