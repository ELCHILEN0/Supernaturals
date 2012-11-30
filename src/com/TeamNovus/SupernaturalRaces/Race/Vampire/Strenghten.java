package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Strenghten implements Spell {

	@Override
	public String name() {
		return "Strenghten";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 300, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 2)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		sender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 30, 0));
		sender.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 30, 0));
		sender.sendMessage(ChatColor.GOLD + "You strengthen with your Vampiric powers!");
		return true;
	}

}
