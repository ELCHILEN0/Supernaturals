package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class KillDeathListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		LivingEntity killed = event.getEntity();
		Player killer = event.getEntity().getKiller();
		
		if(killed instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) killed);

			player.setDeaths(player.getDeaths() + 1);
		}
		
		if(killer != null) {
			SNPlayer player = SNPlayers.i.get(killer);
			
			if(!(player.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
				player.setKills(player.getKills() + 1);
			}
		}
	}
	
}
