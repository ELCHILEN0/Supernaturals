package com.TeamNovus.SupernaturalRaces.Race.Demon;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class Explode implements Spell {

	@Override
	public String name() {
		return "Explode";
	}

	@Override
	public String info() {
		return "Damage nearby players in a radius of 5!";
	}

	@Override
	public Material binding() {
		return Material.SULPHUR;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 200, new ItemBag(new ItemStack(Material.SULPHUR, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
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
