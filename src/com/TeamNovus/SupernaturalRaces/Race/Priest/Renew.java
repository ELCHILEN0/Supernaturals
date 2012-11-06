package com.TeamNovus.SupernaturalRaces.Race.Priest;

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

public class Renew implements Spell {

	@Override
	public String name() {
		return "Renew";
	}

	@Override
	public String info() {
		return "Give nearby players incrased damage and damage resistance for 10 seconds!";
	}

	@Override
	public Material binding() {
		return Material.GLOWSTONE_DUST;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		for(Entity entity : sender.getNearbyEntities(5, 5, 5)) {
			if(entity instanceof Player) {
				Player player = ((Player) entity);
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 0));
				player.playEffect(sender.getLocation(), Effect.POTION_BREAK, 0);
				player.sendMessage(ChatColor.GOLD + sender.getDisplayName() + " has rejuvenated you!");
			}
		}
		sender.sendMessage(ChatColor.GOLD + "You have renewed your teamates!");
		return true;
	}

}
