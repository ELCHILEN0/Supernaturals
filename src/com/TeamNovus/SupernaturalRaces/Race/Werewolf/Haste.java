package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Haste implements Spell {

	@Override
	public String name() {
		return "Haste";
	}



	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 200, new ItemBag(new ItemStack(Material.SUGAR, 4)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		sender.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 30, 1));
		sender.sendMessage(ChatColor.GOLD + "Your canine side begins to take over speeding you up!");
		return true;
	}

}
