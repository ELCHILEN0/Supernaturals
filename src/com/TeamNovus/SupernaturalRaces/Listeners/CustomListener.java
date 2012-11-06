package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageByEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

/**
 * This class is designed to listen to and trigger any custom listeners
 *
 */
public class CustomListener implements Listener {
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			PlayerDamageEvent newEvent = new PlayerDamageEvent(
										   (Player) event.getEntity(),
										   event.getCause(),
										   event.getDamage());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);
			
			event.setDamage(newEvent.getDamage());
			
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}		
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {	
		if(event.getDamager() instanceof Player) {
			PlayerDamageEntityEvent newEvent = new PlayerDamageEntityEvent(
												   (Player) event.getDamager(),
												   event.getEntity(),
												   event.getCause(),
												   event.getDamage());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());
						
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
		
		if(event.getEntity() instanceof Player) {
			PlayerDamageByEntityEvent newEvent = new PlayerDamageByEntityEvent(
												   (Player) event.getEntity(),
												   event.getEntity(),
												   event.getCause(),
												   event.getDamage());
			
			SupernaturalRaces.getPlugin().getServer().getPluginManager().callEvent(newEvent);

			event.setDamage(newEvent.getDamage());
						
			if(newEvent.isCancelled()) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		// Check to see if the player is actually clicking the air
		if(event.getAction().equals(Action.LEFT_CLICK_AIR)) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(event.getPlayer());
			Race race = SupernaturalRaces.getRaceManager().getRace(player);
						
			for(Spell spell : race.spells()) {
				if(spell.binding().equals(event.getMaterial())) {
					if(spell.consume().has(event.getPlayer())) {
						if(spell.execute(event.getPlayer())) {
							spell.consume().consume(event.getPlayer());
						}
					} else {
						event.getPlayer().sendMessage(ChatColor.RED + "You do not have the required reagents to cast this spell!");
						event.getPlayer().sendMessage(ChatColor.RED + "To see information on a certain spell do /sn spell <Spell>!");
					}
				}
			}
		}
	}
}
