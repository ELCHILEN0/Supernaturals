package com.TeamNovus.SupernaturalRaces.Race.Priest;

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

public class HolyBuff implements SNSpell {

	@Override
	public String name() {
		return "HolyBuff";
	}

	@Override
	public String desc() {
		return "Buffer nearby players with holy spells!";
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
		bindings.add(Material.BOWL);
		return bindings;
	}

	@Override
	public Integer power() {
		return 35;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		for(Entity e : sender.getNearbyEntities(5, 5, 5)) {
			if(e instanceof Player) {
				((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0));
				((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 0));
				((Player) e).sendMessage(ChatColor.YELLOW + sender.getName() + " has gifted you with buffs!");
			}
		}
		sender.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0));
		sender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 10, 0));
		sender.sendMessage(ChatColor.YELLOW + "You have given yourself buffs!");
		
		return true;
	}

}
