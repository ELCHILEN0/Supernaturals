package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Cure implements SNSpell {

	@Override
	public String name() {
		return "Cure";
	}

	@Override
	public String desc() {
		return "Cure yourself of all maladies!";
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
		bindings.add(Material.CLAY_BALL);
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
		sender.setHealth(sender.getMaxHealth());
		sender.removePotionEffect(PotionEffectType.BLINDNESS);
		sender.removePotionEffect(PotionEffectType.CONFUSION);
		sender.removePotionEffect(PotionEffectType.HARM);
		sender.removePotionEffect(PotionEffectType.HUNGER);
		sender.removePotionEffect(PotionEffectType.POISON);
		sender.removePotionEffect(PotionEffectType.SLOW);
		sender.removePotionEffect(PotionEffectType.SLOW_DIGGING);
		sender.removePotionEffect(PotionEffectType.WEAKNESS);
		sender.removePotionEffect(PotionEffectType.WITHER);
		sender.sendMessage(ChatColor.GOLD + "You have been cleared of all your maladies!");
		return true;
	}
}
