package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;


import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class KillDeathListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		
		if(event.getEntity() instanceof Player) {
			SNPlayer killed = SNPlayer.getPlayer((Player) event.getEntity());

			killed.setDeaths(killed.getDeaths() + 1);
			killed.save();

			if(event.getEntity().getKiller() != null) {
				SNPlayer killer = SNPlayer.getPlayer((Player) event.getEntity().getKiller());
				
				if(!(killer.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
					killer.setKills(killer.getKills() + 1);
					killer.save();
				}
			}
		}
	}
	
}
