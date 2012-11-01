package com.TeamNovus.SupernaturalRaces;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class ResetRaceAttributeTask {
	
	/**
	 * Schedule a value to be set to a different value after some time.
	 * Uses the SNPlayer attribute HashMap that can accept any type of object.
	 * 
	 * @param plugin - An instance of the plugin
	 * @param player - The player to use when resetting
	 * @param ticks - The ammount of time before resetting the value
	 * @param key - The key to set
	 * @param value - The value to set the key to when resetting
	 */
	public ResetRaceAttributeTask(final SupernaturalRaces plugin, final Player player, final Integer ticks, final String key, final Object value) {		
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				SNPlayer p = plugin.getPlayerManager().getPlayer(player);
				p.setAttribute(key, value);
			}
		}, ticks);
		
	}
}
