package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Vanish implements Spell {

	@Override
	public String name() {
		return "Vanish";
	}

	@Override
	public Material binding() {
		return Material.SUGAR;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 300, new ItemBag(new ItemStack(Material.SUGAR, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		if(sender.getWorld().getTime() < 12000) {
			sender.sendMessage(ChatColor.RED + "Your powers are ineffective in daytime.");
			return false;
		}
		
		sender.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 60, 0));
		sender.sendMessage(ChatColor.GOLD + "You blend in with the night!");
		return true;
	}

}
