package com.TeamNovus.Supernaturals.Custom.Effect.EffectTypes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectTypeListener;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class Revive extends EffectType {

	public void onEffectBegin(EntityEffectBeginEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.sendMessage(ChatColor.GREEN + "You gain the ability to occasionaly revive yourself!");
		}
	}
	
	@EffectTypeListener(priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent event, Effect effect) {
		if(event.getEntity() instanceof Damageable) {
			Damageable entity = (Damageable) event.getEntity();
			
			if(entity.getHealth() - event.getDamage() <= 0) {
				if(new Random().nextInt(101) <= effect.getAmplifier()) {
					event.setCancelled(true);
					entity.setHealth(2 * effect.getAmplifier());
					effect.end();
					
					if(entity instanceof Player) {
						SNPlayer player = SNPlayers.i.get((Player) entity);
						
						player.sendMessage(ChatColor.GREEN + "You have risen from the dead!");
					}
				}
			}
		}
	}
	
	public void onEffectExpire(EntityEffectExpireEvent event) {
		if(event.getEntity() instanceof Player) {
			SNPlayer player = SNPlayers.i.get((Player) event.getEntity());
			
			player.sendMessage(ChatColor.RED + "You no longer have the ability to revive yourself!");				
		}
	}

}
