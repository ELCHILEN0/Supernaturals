package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Cure implements Spell {
	@Override
	public String name() {
		return "Cure";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 100, new ItemBag(new ItemStack(Material.GLOWSTONE_DUST, 10)));
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
				int cured = 0;
				if(player.getItemInHand().getType().equals(Material.getMaterial(373))) {
					player.setItemInHand(new ItemStack(Material.GLASS_BOTTLE, player.getItemInHand().getAmount()));
					player.sendMessage(ChatColor.GOLD + sender.getName() + " has cured you!");
					sender.sendMessage(ChatColor.GOLD + "You have cured " + player.getName() + " !");
					cured++;
				}
				
				if(cured == 0) {
					sender.sendMessage(ChatColor.RED + "No players wish to be cured!");
					return false;
				}
			}
		}
		return true;
	}
}
