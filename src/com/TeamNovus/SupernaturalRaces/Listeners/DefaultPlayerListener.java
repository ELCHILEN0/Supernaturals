package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;

public class DefaultPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
		if(player.getRemainingIceWalk() > 0) {
			int radius = 2;
			for (int x = -(radius); x <= radius; x++) {
					for (int z = -(radius); z <= radius; z++) {
						Block block = event.getPlayer().getLocation().getBlock().getRelative(x, -1, z);
						if(block.getType().equals(Material.WATER) || block.getType().equals(Material.STATIONARY_WATER))
							block.setType(Material.ICE);
					}

			}
		}
	}

}
