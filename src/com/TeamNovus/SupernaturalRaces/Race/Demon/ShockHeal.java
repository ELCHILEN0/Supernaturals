package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class ShockHeal implements SNSpell {

	@Override
	public String name() {
		return "ShockHeal";
	}

	@Override
	public String desc() {
		return "Heal yourself with a strike of lightning!";
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
		bindings.add(Material.GLOWSTONE_DUST);
		return bindings;
	}

	@Override
	public Integer power() {
		return 30;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		sender.getWorld().strikeLightning(sender.getLocation());
		sender.setHealth(sender.getMaxHealth());
		sender.sendMessage(ChatColor.GOLD + "Lighting has struck and has healed you!");
		return true;
	}

}
