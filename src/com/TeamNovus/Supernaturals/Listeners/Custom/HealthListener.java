package com.TeamNovus.Supernaturals.Listeners.Custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Util.DamageUtil;

public class HealthListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			int newHealth = player.getHealth() - DamageUtil.getDamage(player.getPlayer(), event.getDamage(), event.getCause());
			
			// Check if the blow is a killing blow and if so process the damage as normally.
			if(newHealth > 0) {
				player.setHealth(newHealth);
				event.setDamage(0);
			} else {
				event.setDamage(player.getPlayer().getMaxHealth());
			}
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
						
			player.setHealth(player.getHealth() + event.getAmount());
			
			event.setAmount(0);
		}
	}	
}
