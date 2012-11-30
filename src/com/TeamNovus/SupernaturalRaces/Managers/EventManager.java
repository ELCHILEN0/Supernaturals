package com.TeamNovus.SupernaturalRaces.Managers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityByProjectileEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class EventManager implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		invokeEvents(event.getPlayer(), event);
	}
	
	@EventHandler
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		invokeEvents(event.getPlayer(), event);
	}
	
	@EventHandler
	public void onPlayerDamageEntityByProjectileEvent(PlayerDamageEntityByProjectileEvent event) {
		invokeEvents(event.getPlayer(), event);
	}
	
	@EventHandler
	public void onPlayerDamage(PlayerDamageEvent event) {
		invokeEvents(event.getPlayer(), event);
	}
	
	@EventHandler
	public void onPlayerDamageByEntity(PlayerDamageEntityEvent event) {
		invokeEvents(event.getPlayer(), event);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		invokeEvents(event.getPlayer(), event);
	}
	
	@EventHandler
	public void onPlayerKillEntity(EntityDeathEvent event) {
		invokeEvents(event.getEntity().getKiller(), event);
	}
	
	/**
	 * Invoke an event for a player depending on the race!
	 * @param sender - The MAIN player to determine the race
	 * @param event - The event to trigger
	 */
	public void invokeEvents(Player sender, Event event) {		
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(sender);
		Race race = SupernaturalRaces.getRaceManager().getRace(player);
		
		for(Class<? extends SNEventListener> c : race.events()) {
			for(Method method : c.getMethods()) {
				if(method.isAnnotationPresent(SNEventHandler.class)) {
					for(Type type : method.getParameterTypes()) {
						if(type.equals(event.getClass())) {
							try {
								try {
									method.invoke(method.getDeclaringClass().newInstance(), event);
								} catch (InstantiationException e) {
									e.printStackTrace();
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
			}	
		}
	}

}
