package com.TeamNovus.SupernaturalRaces.Spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class HolyHeal implements SNSpell {

	@Override
	public String name() {
		return "HolyHeal";
	}

	@Override
	public String desc() {
		return "Heal yourself and your nearby players!";
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
		return 25;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		for(Entity e : sender.getNearbyEntities(5, 5, 5)) {
			if(e instanceof Player) {
				((Player) e).setHealth(((Player) e).getHealth() + 5);
				((Player) e).sendMessage(ChatColor.YELLOW + sender.getName() + " has healed you!");
			}
		}
		sender.setHealth(sender.getHealth() + 5);
		sender.sendMessage(ChatColor.YELLOW + "You have healed yourself!");
		return true;
	}
	
}
