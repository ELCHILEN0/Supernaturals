package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Renew implements SNSpell {

	@Override
	public String name() {
		return "Renew";
	}

	@Override
	public String desc() {
		return "Bring your comrades back into the fight!";
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
		return 150;
	}

	@Override
	public Boolean consume() {
		return true;
	}

	@Override
	public Boolean execute(Player sender) {
		for(Entity entity : sender.getNearbyEntities(5, 5, 5)) {
			if(entity instanceof Player) {
				Player player = ((Player) entity);
				player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20 * 10, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 0));
				player.playEffect(sender.getLocation(), Effect.POTION_BREAK, 0);
				player.sendMessage(ChatColor.GOLD + sender.getDisplayName() + " has rejuvenated you!");
			}
		}
		sender.sendMessage(ChatColor.GOLD + "You have renewed your teamates!");
		return true;
	}

}
