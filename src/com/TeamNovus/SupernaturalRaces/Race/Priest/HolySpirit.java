package com.TeamNovus.SupernaturalRaces.Race.Priest;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Metadata.SNBooleanAttribute;
import com.TeamNovus.SupernaturalRaces.Models.Reagent;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

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
		return new Reagent(0.0, 0, 0, 0, new ItemStack(Material.SUGAR, 5), 400);
	}

	@Override
	public Reagent consume() {
		return required();
	}
	
	@Override
	public Boolean execute(final Player sender) {
		
		SupernaturalRaces.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalRaces.getPlugin(),
				new Runnable() {
					@Override
					public void run() {
						SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(sender);
						player.setAttribute("phaseWalking", new SNBooleanAttribute(false));
						sender.sendMessage(ChatColor.GOLD + "You have returned to your body!");
						sender.playEffect(sender.getLocation(), Effect.SMOKE, 0);
					}
			}, 20 * 30);
		
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(sender);
		player.setAttribute("phaseWalking", new SNBooleanAttribute(true));
		sender.sendMessage(ChatColor.GOLD + "You have left your body!");
		sender.playEffect(sender.getLocation(), Effect.SMOKE, 0);
		return true;
	}
}
