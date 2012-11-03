package com.TeamNovus.SupernaturalRaces.Race.Angel;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Soar implements SNSpell {

	@Override
	public String name() {
		return "Soar";
	}

	@Override
	public String desc() {
		return "Soar above the heavens!";
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
		bindings.add(Material.FEATHER);
		return bindings;
	}

	@Override
	public Integer power() {
		return 10;
	}

	@Override
	public Boolean consume() {
		return true;
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