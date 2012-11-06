package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Models.Reagent;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class FireRing implements Spell {

	@Override
	public String name() {
		return "FireRing";
	}

	@Override
	public String info() {
		return "Cast a fire ring around you!";
	}

	@Override
	public Material binding() {
		return Material.REDSTONE;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, new ItemStack(Material.REDSTONE, 5), 300);
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		List<Location> locations = new ArrayList<Location>();
		// Section 1
		locations.add(sender.getLocation().add(0, 0, 2));
		locations.add(sender.getLocation().add(0, 0, 4));
		locations.add(sender.getLocation().add(1, 0, 1));
		locations.add(sender.getLocation().add(1, 0, 4));
		locations.add(sender.getLocation().add(2, 0, 0));
		locations.add(sender.getLocation().add(2, 0, 3));
		locations.add(sender.getLocation().add(3, 0, 2));
		locations.add(sender.getLocation().add(3, 0, 3));
		locations.add(sender.getLocation().add(4, 0, 0));
		locations.add(sender.getLocation().add(4, 0, 1));
		locations.add(sender.getLocation().add(3, 0, 2));
		
		// Section 2
		locations.add(sender.getLocation().add(0, 0, -2));
		locations.add(sender.getLocation().add(0, 0, -4));
		locations.add(sender.getLocation().add(1, 0, -1));
		locations.add(sender.getLocation().add(1, 0, -4));
		locations.add(sender.getLocation().add(2, 0, -0));
		locations.add(sender.getLocation().add(2, 0, -3));
		locations.add(sender.getLocation().add(3, 0, -2));
		locations.add(sender.getLocation().add(3, 0, -3));
		locations.add(sender.getLocation().add(4, 0, -0));
		locations.add(sender.getLocation().add(4, 0, -1));
		locations.add(sender.getLocation().add(3, 0, -2));
		
		// Section 3
		locations.add(sender.getLocation().add(-0, 0, -2));
		locations.add(sender.getLocation().add(-0, 0, -4));
		locations.add(sender.getLocation().add(-1, 0, -1));
		locations.add(sender.getLocation().add(-1, 0, -4));
		locations.add(sender.getLocation().add(-2, 0, -0));
		locations.add(sender.getLocation().add(-2, 0, -3));
		locations.add(sender.getLocation().add(-3, 0, -2));
		locations.add(sender.getLocation().add(-3, 0, -3));
		locations.add(sender.getLocation().add(-4, 0, -0));
		locations.add(sender.getLocation().add(-4, 0, -1));
		locations.add(sender.getLocation().add(-3, 0, -2));
		
		// Section 4
		locations.add(sender.getLocation().add(-0, 0, 2));
		locations.add(sender.getLocation().add(-0, 0, 4));
		locations.add(sender.getLocation().add(-1, 0, 1));
		locations.add(sender.getLocation().add(-1, 0, 4));
		locations.add(sender.getLocation().add(-2, 0, 0));
		locations.add(sender.getLocation().add(-2, 0, 3));
		locations.add(sender.getLocation().add(-3, 0, 2));
		locations.add(sender.getLocation().add(-3, 0, 3));
		locations.add(sender.getLocation().add(-4, 0, 0));
		locations.add(sender.getLocation().add(-4, 0, 1));
		locations.add(sender.getLocation().add(-3, 0, 2));
		
		List<Block> before = new ArrayList<Block>();
		for(Location l : locations) {
			if(l.getBlock().getType().equals(Material.AIR)) {
				before.add(l.getBlock());
				l.getBlock().setType(Material.FIRE);
			}
		}
		sender.sendMessage(ChatColor.GOLD + "A ring of fire has been cast around you!");
		return true;
	}

}
