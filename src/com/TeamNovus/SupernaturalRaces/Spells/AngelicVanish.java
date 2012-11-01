package com.TeamNovus.SupernaturalRaces.Spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class AngelicVanish implements SNSpell {
	
	@Override
	public String name() {
		return "AngelicVanish";
	}

	@Override
	public String desc() {
		return "Vanish for a short period of time!";
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
		bindings.add(Material.BOOK);
		return bindings;
	}

	@Override
	public Integer power() {
		return 40;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 5, 0));
		return true;
	}
}
