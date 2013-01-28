package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class HungerListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.setFoodLevel(player.getFoodLevel() + (event.getFoodLevel() - ((Player) event.getEntity()).getFoodLevel()));
			
			event.setCancelled(true);
		}
	}
	
}
