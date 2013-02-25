package com.TeamNovus.Supernaturals.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Persistence.Annotations.Column;
import com.TeamNovus.Persistence.Annotations.Key;
import com.TeamNovus.Persistence.Annotations.Table;
import com.TeamNovus.Supernaturals.Entity.Effects.Effect;
import com.TeamNovus.Supernaturals.Entity.Effects.LastingEffect;
import com.TeamNovus.Supernaturals.Entity.Effects.LastingPeriodicEffect;
import com.TeamNovus.Supernaturals.Entity.Effects.PeriodicEffect;
import com.TeamNovus.Supernaturals.Events.EntityEffectBeginEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectExpireEvent;
import com.TeamNovus.Supernaturals.Events.EntityEffectTriggerEvent;

@Table(name = "sn_entities")
public class SNEntity {
	@Key
	@Column(name = "id")
	private int id;
	
	@Column(name = "uuid")
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
			
			if (effect instanceof LastingEffect) {
				LastingEffect lastingEffect = (LastingEffect) effect;
				
				if (lastingEffect.getElapsed() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectBeginEvent(getEntity(), lastingEffect));
				}

				if (lastingEffect.getElapsed() >= lastingEffect.getDuration()) {
					effectIterator.remove();
					Bukkit.getPluginManager().callEvent(new EntityEffectExpireEvent(getEntity(), lastingEffect));
				}				
			}
			
			if (effect instanceof PeriodicEffect) {
				PeriodicEffect periodicEffect = (PeriodicEffect) effect;
				
				if (periodicEffect.getElapsed() % periodicEffect.getPeriod() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectTriggerEvent(getEntity(), periodicEffect));
				}
			}
			
			if (effect instanceof LastingPeriodicEffect) {
				LastingPeriodicEffect lastingPeriodicEffect = (LastingPeriodicEffect) effect;
				
				if (lastingPeriodicEffect.getElapsed() % lastingPeriodicEffect.getPeriod() == 0) {
					Bukkit.getPluginManager().callEvent(new EntityEffectTriggerEvent(getEntity(), lastingPeriodicEffect));
				}
			}

			effect.setElapsed(effect.getElapsed() + 1);
		}
	}

}
