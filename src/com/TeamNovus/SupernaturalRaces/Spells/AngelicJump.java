package com.TeamNovus.SupernaturalRaces.Spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class AngelicJump implements SNSpell {

	@Override
	public String name() {
		return "AngelicJump";
	}

	@Override
	public String desc() {
		return "Jump like an angel!";
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
		return 20;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player player) {
		if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
			Vector apply = new Vector(0, 1, 0);
			apply.multiply(1);
			player.setVelocity(player.getVelocity().add(apply));
			return true;
		}
		return false;
	}

}
