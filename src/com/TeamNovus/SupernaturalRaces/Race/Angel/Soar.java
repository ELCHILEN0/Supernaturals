package com.TeamNovus.SupernaturalRaces.Race.Angel;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Soar implements Spell {

	@Override
	public String name() {
		return "Soar";
	}


	
	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 100, new ItemBag(new ItemStack(Material.FEATHER, 2)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		if(sender.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
			Vector apply = new Vector(0, 1, 0);
			apply.multiply(1.5);
			sender.setVelocity(sender.getVelocity().add(apply));
			sender.sendMessage(ChatColor.GOLD + "You soar above the heavens!");
			return true;
		}
		return false;
	}
}
