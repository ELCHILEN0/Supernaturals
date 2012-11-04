package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Metadata.SNBooleanAttribute;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class HolySpirit implements SNSpell {

	@Override
	public String name() {
		return "HolySpirit";
	}

	@Override
	public String desc() {
		return "Leave your body and attack as a Spirit!";
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
		bindings.add(Material.SUGAR);
		return bindings;
	}

	@Override
	public Integer power() {
		return 400;
	}

	@Override
	public Boolean consume() {
		return true;
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
