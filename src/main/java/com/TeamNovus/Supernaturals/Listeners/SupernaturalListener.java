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
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Events.EntityDamageByEntityProjectileEvent;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityByProjectileEvent;
import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTickEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;
import com.TeamNovus.Supernaturals.Events.PlayerManaChangeEvent;

public class SupernaturalListener implements Listener {

	// Start Effect Events
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

	// Start Mana Change
	@EventHandler
	public void onPlayerManaChangeEvent(PlayerManaChangeEvent event) {
		invokeEvent(event, PlayerManaChangeEvent.class);
	}

	// Start Damage Events
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		invokeEvent(event, EntityDamageEvent.class);
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		invokeEvent(event, EntityDamageByEntityEvent.class);
	}

	@EventHandler
	public void onEntityDamageEntityEvent(EntityDamageEntityEvent event) {
		invokeEvent(event, EntityDamageEntityEvent.class);
	}

	@EventHandler
	public void onEntityDamageByEntityProjectileEvent(EntityDamageByEntityProjectileEvent event) {
		invokeEvent(event, EntityDamageByEntityProjectileEvent.class);
	}

	@EventHandler
	public void onEntityDamageEntityByProjectileEvent(EntityDamageEntityByProjectileEvent event) {
		invokeEvent(event, EntityDamageEntityByProjectileEvent.class);
        System.out.println("SHOOTING EVENT?");
	}

	// Start Player Events
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		invokeEvent(event, PlayerMoveEvent.class);
	}

	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		invokeEvent(event, PlayerRespawnEvent.class);
	}

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        invokeEvent(event, PlayerLoginEvent.class);
    }

	// Start Entity Events
	@EventHandler
	public void onEntityShootBowEvent(EntityShootBowEvent event) {
		invokeEvent(event, EntityShootBowEvent.class);
	}

	@EventHandler
	public void onEntityCombustEvent(EntityCombustEvent event) {
		invokeEvent(event, EntityCombustEvent.class);
	}

	@EventHandler
	public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event) {
		invokeEvent(event, EntityCombustByEntityEvent.class);
	}

	@EventHandler
	public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent event) {
		invokeEvent(event, EntityCombustByBlockEvent.class);
	}

	@EventHandler
	public void onEntityTargetEvent(EntityTargetEvent event) {
		invokeEvent(event, EntityTargetEvent.class);
	}

	@EventHandler
	public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) {
		invokeEvent(event, EntityTargetLivingEntityEvent.class);
	}

	/**
	 * Invoke an event for a BaseEffect for a player or entity.
	 * 
	 * @param event - The event being invoked.
	 * @param eventClass - The type of the event.
	 */
	public void invokeEvent(Event event, Class<? extends Event> eventClass) {
		ArrayList<Effect> effects = new ArrayList<Effect>();

        // Add Entity Effects
		if (event instanceof EntityEvent) {
			Entity e = ((EntityEvent) event).getEntity();

			if(e instanceof LivingEntity && SNEntities.i.attached((LivingEntity) e)) {
				effects.addAll(SNEntities.i.get((LivingEntity) e).getEffects());
			}
		}

        // Add Player Abilities
		if (event instanceof PlayerEvent) {
			effects.addAll(SNPlayers.i.get(((PlayerEvent) event).getPlayer()).getAbilities());
		} else if (event instanceof EntityEvent) {
            Entity e = ((EntityEvent) event).getEntity();

            if(e instanceof Player) {
                effects.addAll(SNPlayers.i.get((Player) e).getAbilities());
            }
        }

		for (Effect effect : effects) {
            System.out.println(effect);
			EffectType effectType = effect.getType();

			for (Method method : effectType.getClass().getMethods()) {
				Type[] types = method.getParameterTypes();
				Object[] params = new Object[types.length];

                boolean isListener = false;
                for (int i = 0; i < types.length; i++) {
                    Type type = types[i];

                    if(type.equals(eventClass)) {
                        params[i] = event;
                        isListener = true;
                    } else if(type.equals(Effect.class)) {
                        params[i] = effect;
                    } else {
                        params[i] = null;
                    }
                }

                if(isListener) {
                    try {
                        method.invoke(effectType, params);
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
