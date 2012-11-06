package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.Reagent;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class Luminatus implements Spell {
	
	@Override
	public String name() {
		return "Luminatus";
	}

	@Override
	public String info() {
		return "Night Vision for 25 seconds!";
	}
	
	@Override
	public Material binding() {
		return Material.GLOWSTONE_DUST;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, new ItemStack(Material.GLOWSTONE_DUST, 1), 150);
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		sender.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 25, 0));
		sender.playEffect(sender.getLocation(), Effect.SMOKE, 1);
		sender.sendMessage(ChatColor.GOLD + "The world lights up before your eyes!");
		return true;
	}
}
