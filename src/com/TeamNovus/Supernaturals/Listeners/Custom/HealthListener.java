package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class HealthListener implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
					
			player.resync();
			
			int newHealth = player.getHealth() - event.getDamage();
			
			if(newHealth > 0) {
				player.setHealth(newHealth);
				event.setDamage(0);
			} else {
				event.setDamage(player.getPlayer().getMaxHealth());
			}
		}
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.setHealth(player.getHealth() + event.getAmount());
									
			event.setAmount(0);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getPlayer());
		
		event.getPlayer().setHealth(player.getMaxHealth());
	}
	
}
