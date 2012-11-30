package com.TeamNovus.SupernaturalRaces.Race.Ranger;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class ExplodingArrow implements Spell {

	@Override
	public String name() {
		return "ExplodingArrows";
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 150, new ItemBag(new ItemStack(Material.TNT, 5), new ItemStack(Material.ARROW, 1)));
	}

	@Override
	public Reagent consume() {
		return required();
	}

	@Override
	public Boolean execute(Player sender) {

		return true;
	}

}
