package com.TeamNovus.SupernaturalRaces.Effects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class IceWalk implements SNEventListener {

	@SNEventHandler
	public void onEffectBeginEvent(EffectBeginEvent event) {
		event.getPlayer().sendMessage(ChatColor.AQUA + "You feel a chill beneath you...");
	}

	@SNEventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		int radius = 2;
		for (int x = -(radius); x <= radius; x++) {
			for (int z = -(radius); z <= radius; z++) {
				Block block = event.getPlayer().getLocation().getBlock().getRelative(x, -1, z);
				if(block.getType().equals(Material.WATER) || block.getType().equals(Material.STATIONARY_WATER))
					block.setType(Material.ICE);
			}
		}
	}

	@SNEventHandler
	public void onEffectExpireEvent(EffectExpireEvent event) {
		event.getPlayer().sendMessage(ChatColor.AQUA + "You can no longer walk on ice!");
	}
}
