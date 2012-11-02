package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class DemonicBlind implements SNSpell {

	@Override
	public String name() {
		return "DemonicBlind";
	}

	@Override
	public String desc() {
		return "Blind nearby targets with your demonic powers!";
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
		return 30;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		for(Entity e : sender.getNearbyEntities(5, 5, 5)) {
			if(e instanceof Player) {
				((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 0));
				((Player) e).sendMessage(ChatColor.RED + "You have been blinded by " + sender.getName() + "!");
			}
		}
		
		return false;
	}
}
