package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Character.SNEntites;
import com.TeamNovus.SupernaturalRaces.Character.SNEntity;
import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTickEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectTriggerEvent;

public class EntityManager {
	private SNEntites entites = new SNEntites();
	
	public SNEntites getEntites() {
		return entites;
	}
	
	public void putEntity(SNEntity entity) {
		entites.put(entity);
	}
	
	public void removeEntity(SNEntity entity) {
		entites.remove(entity);
	}
	
	public void tickAll() {
		Iterator<SNEntity> iterator = entites.iterator();
		while(iterator.hasNext()) {
			SNEntity entity = iterator.next();
			
			if(entity != null && entity.getEntity() != null && entity.getEntity().isValid()) {
				Iterator<SNEffect> effectIterator = entity.getEffects().iterator();
				while(effectIterator.hasNext()) {
					SNEffect effect = effectIterator.next();
					if(effect.getElapsed() == 0) {
						Bukkit.getPluginManager().callEvent(new EffectBeginEvent(entity.getEntity(), effect));					
					}
					
					effect.setElapsed(effect.getElapsed() + 1);
					Bukkit.getPluginManager().callEvent(new EffectTickEvent(entity.getEntity(), effect));					
										
					if(effect.getPeriod() != 0 && effect.getElapsed() % effect.getPeriod() == 0) {
						Bukkit.getPluginManager().callEvent(new EffectTriggerEvent(entity.getEntity(), effect));
					}
					
					if(effect.getDuration() != -1) {
						if(effect.getElapsed() >= effect.getDuration()) {
							Bukkit.getPluginManager().callEvent(new EffectExpireEvent(entity.getEntity(), effect));
							iterator.remove();
						}
					}
				}
			} else {
				iterator.remove();
			}
		}
	}
	
	public void addRaceEffects(Player p) {
		SNEntity entity = getEntity(p);
		entity.addEffects(SupernaturalRaces.getPlayerManager().getPlayer(p).getPlayerRace().effects());
		putEntity(entity);
	}
	
	/**
	 * Get an SNEntity from an entity
	 */
	public SNEntity getEntity(Entity entity) {
		if(entites.get(entity) == null) {
			putEntity(new SNEntity(entity));
		}
		return entites.get(entity);
	}
	
	/**
	 * Get all entites with effects
	 */
	public SNEntites getEntitesWithEffects() {
		SNEntites effectedEntites = new SNEntites();
		
		Iterator<SNEntity> iterator = entites.iterator();
		while(iterator.hasNext()) {
			SNEntity entity = iterator.next();
			if(entity.getEffects().size() > 0) {
				effectedEntites.add(entity);
			}
		}
		
		return effectedEntites;
	}
	
	public SNEntites getEntitesWithEffect(SNEffect e) {
		SNEntites effectedEntites = new SNEntites();
		
		Iterator<SNEntity> iterator = entites.iterator();
		while(iterator.hasNext()) {
			SNEntity entity = iterator.next();
			for(SNEffect effect : entity.getEffects()) {
				if(effect.getType().equals(e.getType())) {
					effectedEntites.add(entity);
					break;
				}
			}
		}
		
		return effectedEntites;
	}
}
