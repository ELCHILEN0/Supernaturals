package com.TeamNovus.SupernaturalRaces.Managers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Character.SNEntity;
import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTriggerEvent;
import com.TeamNovus.SupernaturalRaces.Events.EntityDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.EntityDamageEntityByProjectileEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNEventHandler;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class EventManager implements Listener {
	
	// Player Events:
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
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
	public void onEntityDamageEvent(EntityDamageEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEntityDamageByEntityProjectile(EntityDamageEntityByProjectileEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEntityDamageEntityByProjectile(EntityDamageEntityByProjectileEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	// Effect Events:
	@EventHandler
	public void onEffectBegin(EffectBeginEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEffectExpire(EffectExpireEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEffectTick(EffectTickEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	@EventHandler
	public void onEffectTrigger(EffectTriggerEvent event) {
		invokeEvents(event.getEntity(), event);
	}
	
	/**
	 * Invoke an event for an entity!
	 * @param sender - The MAIN entity of the event.
	 * @param event - The event to trigger
	 */
	public void invokeEvents(Entity sender, Event event) {
		SNEntity entity = SupernaturalRaces.getEntityManager().getEntity(sender);
		
		for(SNEffect effect : entity.getEffects()) {
			Class<? extends SNEventListener> c = effect.getModifiers();
			for(Method method : c.getMethods()) {
				if(method.isAnnotationPresent(SNEventHandler.class)) {
					// TODO: Event, Effect
					Type[] types = method.getParameterTypes();
							
					if(types.length == 1) {
						if(types[0].equals(event.getClass())) {
							try {
								method.invoke(method.getDeclaringClass().newInstance(), event);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (InstantiationException e) {
								e.printStackTrace();
							}
						}
					} else if(types.length == 2) {
						if(types[0].equals(event.getClass()) && types[1].equals(effect.getClass())) {
							try {
								method.invoke(method.getDeclaringClass().newInstance(), event, effect);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (InstantiationException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
