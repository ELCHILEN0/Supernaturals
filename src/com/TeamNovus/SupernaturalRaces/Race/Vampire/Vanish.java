package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Vanish implements SNSpell {

	@Override
	public String name() {
		return "Vanish";
	}

	@Override
	public String desc() {
		return "Vanish from your enemies!";
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
		bindings.add(Material.SUGAR);
		return bindings;
	}

	@Override
	public Integer power() {
		return 300;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		if(sender.getWorld().getTime() < 12000) {
			sender.sendMessage(ChatColor.RED + "Your powers are ineffective in daytime.");
			return false;
		}
		sender.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 60, 0));
		sender.sendMessage(ChatColor.GOLD + "You blend in with the night!");
		return true;
	}

}
