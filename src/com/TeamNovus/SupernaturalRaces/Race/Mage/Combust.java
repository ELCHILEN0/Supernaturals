package com.TeamNovus.SupernaturalRaces.Race.Mage;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Race.Demon.DemonRace;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;
import com.TeamNovus.SupernaturalRaces.Util.SpellUtil;

public class Combust implements Spell {

	@Override
	public String name() {
		return "Combust";
	}
	
	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.REDSTONE, 7)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		LivingEntity entity = SpellUtil.getTargetedEntity(sender, 35);
		
		if(entity == null) {
			sender.sendMessage(ChatColor.RED + "No entity was found in your range!");
			return false;
		}
		
		if(entity instanceof Player && SupernaturalRaces.getPlayerManager().getPlayer((Player) entity).getPlayerRace() instanceof DemonRace) {
			sender.sendMessage(ChatColor.RED + "Demons are immune to fire!");
			return false;
		}
		
		entity.setFireTicks(30 * 20);
		sender.sendMessage(ChatColor.GOLD + "Your enemy has spontaneously combusted!");
		return true;
	}

}