package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class FireRing implements SNSpell {

	@Override
	public String name() {
		return "FireRing";
	}

	@Override
	public String desc() {
		return "Surround yourself with a ring of fire!";
	}

	@Override
	public List<Action> actions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(Action.LEFT_CLICK_AIR);
		return actions;
	}

	@Override
	public List<Material> bindings() {
		List<Material> bindings = new ArrayList<Material>();
		bindings.add(Material.FIREBALL);
		return bindings;
	}

	@Override
	public Integer power() {
		return 50;
	}

	@Override
	public Boolean consume() {
		return true;
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
		
		for(Location l : locations) {
			if(l.getBlock().getType().equals(Material.AIR)) {
				l.getBlock().setType(Material.FIRE);
			}
		}
		sender.sendMessage(ChatColor.GOLD + "A ring of fire has been cast around you!");
		return true;
	}

}
