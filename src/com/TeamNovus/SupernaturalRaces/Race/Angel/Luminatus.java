package com.TeamNovus.SupernaturalRaces.Race.Angel;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Luminatus implements SNSpell {
	
	@Override
	public String name() {
		return "Luminatus";
	}

	@Override
	public String desc() {
		return "Light up the world!";
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
		bindings.add(Material.GLOWSTONE);
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
		sender.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 25, 0));
		sender.playEffect(sender.getLocation(), Effect.SMOKE, 1);
		sender.sendMessage(ChatColor.GOLD + "The world lights up before your eyes!");
		return true;
	}
}
