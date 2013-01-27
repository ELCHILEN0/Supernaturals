package com.TeamNovus.Supernaturals.Listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Entity.Effects.BaseEffect;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class SupernaturalListener implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		invokeEvent(event, EntityDamageEvent.class);
	}
	
	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		invokeEvent(event, PlayerLoginEvent.class);
	}
	
	public void invokeEvent(Event event,  Class<? extends Event> type) {
		ArrayList<BaseEffect> effects = new ArrayList<BaseEffect>();

		SNPlayer player = null;
		
		if (event instanceof EntityEvent) {
			if (((EntityEvent) event).getEntity() instanceof Player) {
				player = SNPlayers.i.get((Player) ((EntityEvent) event).getEntity());
			}
			
		} else if (event instanceof PlayerEvent) {
			player = SNPlayers.i.get(((PlayerEvent) event).getPlayer());
		}
		
		if (player != null)
			effects.addAll(player.getAbilities());

		
		for (BaseEffect effect : effects) {
			for (Method method : effect.getClass().getMethods()) {
				Type[] types = method.getParameterTypes();
				
				if (types.length >= 1 && types[0].equals(type)) {
					try {
						method.invoke(effect, event);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
