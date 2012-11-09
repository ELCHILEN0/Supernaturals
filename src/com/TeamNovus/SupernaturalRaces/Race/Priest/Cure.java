package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Cure implements Spell {

	@Override
	public String name() {
		return "Cure";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.CLAY, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		sender.setHealth(sender.getMaxHealth());
		sender.setFireTicks(0);
		sender.removePotionEffect(PotionEffectType.BLINDNESS);
		sender.removePotionEffect(PotionEffectType.CONFUSION);
		sender.removePotionEffect(PotionEffectType.HARM);
		sender.removePotionEffect(PotionEffectType.HUNGER);
		sender.removePotionEffect(PotionEffectType.POISON);
		sender.removePotionEffect(PotionEffectType.SLOW);
		sender.removePotionEffect(PotionEffectType.SLOW_DIGGING);
		sender.removePotionEffect(PotionEffectType.WEAKNESS);
		sender.removePotionEffect(PotionEffectType.WITHER);
		sender.sendMessage(ChatColor.GOLD + "You have been cleared of all your maladies!");
		return true;
	}
}
