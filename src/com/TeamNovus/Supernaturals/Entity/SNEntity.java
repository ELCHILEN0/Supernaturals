package com.TeamNovus.Supernaturals.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;

public class SNEntity {
	private int id;
	
	private String uuid;
	
	private ArrayList<Effect> effects = new ArrayList<Effect>();

	public SNEntity(LivingEntity e) {
		uuid = e.getUniqueId().toString();
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public UUID getUUID() {
		return UUID.fromString(uuid);
	}

	public LivingEntity getEntity() {
		for (World w : Bukkit.getWorlds()) {
			for (LivingEntity e : w.getLivingEntities()) {
				if (e.getUniqueId().toString().equals(uuid)) {
					return e;
				}
			}
		}

		return null;
	}

	public Boolean isAlive() {
		return getEntity() != null;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<Effect> effects) {
		this.effects = effects;
	}

	public void addEffect(Effect effect) {
		Iterator<Effect> effectIterator = effects.iterator();
		while (effectIterator.hasNext()) {
			Effect e = effectIterator.next();
			if (e.getClass().equals(effect.getClass())) {				
				e = effect;
				return;
			}
		}

		effects.add(effect);
	}

	public void removeEffect(Effect effect) {
		Iterator<Effect> effectIterator = effects.iterator();
		while (effectIterator.hasNext()) {
			Effect e = effectIterator.next();
			if (e.getClass().equals(effect.getClass())) {				
				effectIterator.remove();
			}
		}
	}

	public void tick() {
		if(!(isAlive())) return;
		
		Iterator<Effect> effectIterator = effects.iterator();
		while (effectIterator.hasNext()) {
			Effect effect = effectIterator.next();
			
			if (effect.isLasting()) {		
				if (effect.getElapsed() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectBeginEvent(getEntity(), effect));
				}

				if (effect.isComplete()) {
					effectIterator.remove();
					Bukkit.getPluginManager().callEvent(new EntityEffectExpireEvent(getEntity(), effect));
				}				
			}
			
			if (effect.isPeriodic()) {				
				if (effect.getElapsed() % effect.getPeriod() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectTriggerEvent(getEntity(), effect));
				}
			}

			effect.setElapsed(effect.getElapsed() + 1);
		}
	}

}
