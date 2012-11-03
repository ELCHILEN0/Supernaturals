package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Explode implements SNSpell {

	@Override
	public String name() {
		return "Explode";
	}

	@Override
	public String desc() {
		return "Emit your rage damaging nearby players!";
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
		bindings.add(Material.SULPHUR);
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
		for(Entity entity : sender.getNearbyEntities(5, 5, 5)) {
			 if(entity instanceof LivingEntity) {
				 ((LivingEntity) entity).damage(5, sender);
			 }
		}
		sender.playEffect(sender.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
		sender.sendMessage(ChatColor.GOLD + "You have exploded damaging nearby players!");
		return true;
	}

}
