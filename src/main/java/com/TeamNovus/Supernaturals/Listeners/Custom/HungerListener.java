package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class HungerListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onFoodLevelChange(final FoodLevelChangeEvent event) {
		if(event.isCancelled())
			return;
		
		if(event.getEntity() instanceof Player) {
			final SNPlayer player = SNPlayer.getPlayer((Player) event.getEntity());
			
			player.setFoodLevel(player.getFoodLevel() + (event.getFoodLevel() - ((Player) event.getEntity()).getFoodLevel()));	
			player.save();
			
			// Rescales the client food level bar.
			Bukkit.getScheduler().runTaskAsynchronously(Supernaturals.plugin, new Runnable() {
				
				public void run() {
					player.updateFoodLevel();	
				}
			});
			
			event.setFoodLevel(player.getFoodLevel() * 20 / player.getMaxFoodLevel());
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		SNPlayer player = SNPlayer.getPlayer(event.getPlayer());

		player.setFoodLevel(player.getMaxFoodLevel());
		player.save();
		
		player.updateFoodLevel();
	}
		
}
