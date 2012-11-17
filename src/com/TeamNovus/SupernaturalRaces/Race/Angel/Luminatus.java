package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Luminatus implements Spell {
	
	@Override
	public String name() {
		return "Luminatus";
	}


	
	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 3)));
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
