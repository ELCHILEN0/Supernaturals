package com.TeamNovus.Supernaturals.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Collections.Entity;
import com.TeamNovus.Supernaturals.Entity.Effects.Effect;
import com.TeamNovus.Supernaturals.Entity.Effects.LastingEffect;
import com.TeamNovus.Supernaturals.Entity.Effects.LastingPeriodicEffect;
import com.TeamNovus.Supernaturals.Entity.Effects.PeriodicEffect;

public class SNEntity extends Entity {
	private UUID uuid;
	private ArrayList<Effect> effects = new ArrayList<Effect>();

	public SNEntity(LivingEntity e) {
		uuid = e.getUniqueId();
	}

	public UUID getUUID() {
		return uuid;
	}

	public LivingEntity getEntity() {
		for (World w : Bukkit.getWorlds()) {
			for (LivingEntity e : w.getLivingEntities()) {
				if (e.getUniqueId().equals(uuid)) {
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
		Iterator<Effect> effectIterator = effects.iterator();
		while (effectIterator.hasNext()) {
			Effect effect = effectIterator.next();
			
			if (effect instanceof LastingEffect) {
				LastingEffect lastingEffect = (LastingEffect) effect;
				
				if (lastingEffect.getElapsed() == 0) {

				}

				if (lastingEffect.getElapsed() >= lastingEffect.getDuration()) {
					removeEffect(lastingEffect);
				}				
			}
			
			if (effect instanceof PeriodicEffect) {
				PeriodicEffect periodicEffect = (PeriodicEffect) effect;
				
				if (periodicEffect.getElapsed() % periodicEffect.getPeriod() == 0) {
					
				}
			}
			
			if (effect instanceof LastingPeriodicEffect) {
				LastingPeriodicEffect lastingPeriodicEffect = (LastingPeriodicEffect) effect;
				
				if (lastingPeriodicEffect.getElapsed() % lastingPeriodicEffect.getPeriod() == 0) {
					
				}
			}

			effect.setElapsed(effect.getElapsed() + 1);
		}
	}

}
