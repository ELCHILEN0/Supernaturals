package com.TeamNovus.SupernaturalRaces.Race.Mage;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;
import com.TeamNovus.SupernaturalRaces.Util.SpellUtil;

public class Combust implements Spell {

	@Override
	public String name() {
		return "Freeze";
	}

	@Override
	public String info() {
		return "Cause a player to combust!";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.BLAZE_ROD, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Material binding() {
		return Material.BLAZE_ROD;
	}

	@Override
	public Boolean execute(Player sender) {
		LivingEntity entity = SpellUtil.getTargetedEntity(sender, 20);
		
		if(entity == null) {
			sender.sendMessage(ChatColor.RED + "No entity was found!");
			return false;
		}
		
		entity.setFireTicks(30 * 20);
		sender.sendMessage(ChatColor.GOLD + "Your enemy has spontaneously combusted!");
		return true;
	}

}