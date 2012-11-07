package com.TeamNovus.SupernaturalRaces.Race.Priest;

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

public class HolySpirit implements Spell {

	@Override
	public String name() {
		return "HolySpirit";
	}

	@Override
	public String info() {
		return "Leave your body and attack as a Spirit!";
	}

	@Override
	public Material binding() {
		return Material.SUGAR;
	}

	@Override
	public Reagent required() {
		return new Reagent(0.0, 0, 0, 0, 400, new ItemBag(new ItemStack(Material.SUGAR, 10)));
	}

	@Override
	public Reagent consume() {
		return required();
	}
	
	@Override
	public Boolean execute(Player sender) {
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(sender);
		player.setRemainingPhaseWalk(player.getRemainingPhaseWalk() + 20 * 30);
		sender.sendMessage(ChatColor.GOLD + "You have left your body!");
		sender.playEffect(sender.getLocation(), Effect.SMOKE, 0);
		return true;
	}
}
