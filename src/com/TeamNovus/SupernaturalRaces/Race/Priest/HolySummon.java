package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class HolySummon implements SNSpell {

	@Override
	public String name() {
		return "HolySummon";
	}

	@Override
	public String desc() {
		return "Summon a docile creature to the world!";
	}

	@Override
	public List<Action> actions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(Action.LEFT_CLICK_AIR);
		actions.add(Action.LEFT_CLICK_BLOCK);
		return actions;
	}

	@Override
	public List<Material> bindings() {
		List<Material> bindings = new ArrayList<Material>();
		bindings.add(Material.BOOK);
		return bindings;
	}

	@Override
	public Integer power() {
		return 15;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		sender.getWorld().spawnEntity(sender.getLocation(), EntityType.COW);
		return true;
	}
	
}
