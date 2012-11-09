package com.TeamNovus.SupernaturalRaces.Race.Mage;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public class IceWalk implements Spell {

	@Override
	public String name() {
		return "IceWalk";
	}



	@Override
	public Material binding() {
		return Material.SUGAR;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 300, new ItemBag(new ItemStack(Material.SUGAR, 5)));
	}

	@Override
	public Reagent consume() {
		return required();
	}
	
	@Override
	public Boolean execute(Player sender) {
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(sender);
		player.setRemainingIceWalk(player.getRemainingIceWalk() + 20 * 60);
		sender.sendMessage(ChatColor.GOLD + "You feet chill beneath you!");
		sender.playEffect(sender.getLocation(), Effect.SMOKE, 0);
		return true;
	}
}