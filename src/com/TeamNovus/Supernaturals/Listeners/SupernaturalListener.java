package com.TeamNovus.Supernaturals.Listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Entity.Effects.BaseEffect;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;

public class SupernaturalListener implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		invokeEvent(event, EntityDamageEvent.class);
	}
	
	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		invokeEvent(event, PlayerLoginEvent.class);
	}

	@EventHandler
	public void onEntityEffectBeginEvent(EntityEffectBeginEvent event) {
		invokeEvent(event, EntityEffectBeginEvent.class);
	}
	
	@EventHandler
	public void onEntityEffectTickEvent(EntityEffectTickEvent event) {
		invokeEvent(event, EntityEffectTickEvent.class);
	}
	
	@EventHandler
	public void onEntityEffectTriggerEvent(EntityEffectTriggerEvent event) {
		invokeEvent(event, EntityEffectTriggerEvent.class);
	}
	
	@EventHandler
	public void onEntityEffectExpireEvent(EntityEffectExpireEvent event) {
		invokeEvent(event, EntityEffectExpireEvent.class);
	}
	
	public void invokeEvent(Event event,  Class<? extends Event> type) {
		ArrayList<BaseEffect> effects = new ArrayList<BaseEffect>();
		
		if (event instanceof EntityEvent) {
			Entity e = ((EntityEvent) event).getEntity();
			
			if(e instanceof Player) {
				effects.addAll(SNPlayers.i.get((Player) e).getAbilities());
			}
			
			if(e instanceof LivingEntity) {
				effects.addAll(SNEntities.i.get((LivingEntity) e).getEffects());
				
				for(BaseEffect effect : SNEntities.i.get((LivingEntity) e).getEffects()) {
					for (Method method : effect.getClass().getMethods()) {
						System.out.println(method.getName());
					}
				}
			}
		}
		
		for (BaseEffect effect : effects) {
			for (Method method : effect.getClass().getMethods()) {
				Type[] types = method.getParameterTypes();
				
				System.out.println(method.getName());
				
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
