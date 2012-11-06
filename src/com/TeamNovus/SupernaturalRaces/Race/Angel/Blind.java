package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Blind implements Spell {

	@Override
	public String name() {
		return "Blind";
	}

	@Override
	public String info() {
		return "Blinds nearby players for 20 seconds!";
	}

	@Override
	public Material binding() {
		return Material.SUGAR;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 200, new ItemBag(new ItemStack(Material.SUGAR, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		for(Entity e : sender.getNearbyEntities(5, 5, 5)) {
			if(e instanceof Player) {
				Player target = ((Player) e);
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 30, 0));
				target.playEffect(target.getLocation(), Effect.SMOKE, 1);
			}
		}
		sender.sendMessage(ChatColor.GOLD + "Your enemies have been blinded!");
		return true;
	}

}
