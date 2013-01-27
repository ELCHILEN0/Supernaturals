package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class HealthListener implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
						
			System.out.println(event.getDamage());
			System.out.println(player.getHealth() + "/" + player.getMaxHealth());
			player.setHealth(player.getHealth() - event.getDamage());
			System.out.println(player.getHealth() + "/" + player.getMaxHealth());

			event.setDamage(0);
			
//			if(player.getHealth() == 0)
//				event.setDamage(player.getPlayer().getHealth());
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
		
		player.setHealth(player.getMaxHealth());
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		SNPlayer player = SNPlayers.i.get(event.getEntity());
		
		player.setHealth(player.getMaxHealth());
	}
	
}
