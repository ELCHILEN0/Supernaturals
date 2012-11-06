package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.Models.Reagent;
import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.SpellUtil;

public class Train implements Spell {
	
	@Override
	public String name() {
		return "WolfSummon";
	}

	@Override
	public String info() {
		return "Summon a wolf to be your loyal companion!";
	}

	@Override
	public Material binding() {
		return Material.BONE;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, new ItemStack(Material.BONE, 4), 50);
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {
		Wolf wolf = (Wolf)sender.getWorld().spawnEntity(sender.getLocation(), EntityType.WOLF);
		wolf.setTamed(true);
		wolf.setOwner(sender);
		wolf = SpellUtil.setCollarColor(wolf, DyeColor.GRAY);
		return true;
	}
}
