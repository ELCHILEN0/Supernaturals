package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect.SNEffectType;
import com.TeamNovus.SupernaturalRaces.Events.EffectBeginEvent;
import com.TeamNovus.SupernaturalRaces.Events.EffectExpireEvent;

public class SNEntity {
	private UUID uniqueId;
	private ArrayList<SNEffect> effects = new ArrayList<SNEffect>();
	
	public SNEntity(Entity e) {
		this.uniqueId = e.getUniqueId();
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public ArrayList<SNEffect> getEffects() {
		return effects;
	}
	
	public void addEffect(SNEffect effect) {
		boolean call = true;
		for(SNEffect e : effects) {
			if(e.equals(effect)) {
				if(e.getDuration() != -1) {
					effect.setDuration(effect.getDuration() + e.getDuration());
				}
				call = false;
			}
		}	
		
		effects.add(effect);
		
		if(call) {
			Bukkit.getPluginManager().callEvent(new EffectBeginEvent(getEntity(), effect));
		}
	}
	
	public void addEffects(ArrayList<SNEffect> effects) {
		for(SNEffect effect : effects) {
			addEffect(effect);
		}
	}
	
	public void removeEffect(SNEffectType type) {
		Iterator<SNEffect> iterator = effects.iterator();
		while(iterator.hasNext()) {
			SNEffect effect = iterator.next();
			if(effect.getType().equals(type)) {
				iterator.remove();
				Bukkit.getPluginManager().callEvent(new EffectExpireEvent(getEntity(), effect));
			}
		}
	}

	public void setEffects(ArrayList<SNEffect> effects) {
		this.effects = effects;
	}
	
	// Bukkit:
	public Entity getEntity() {
		for(World world : Bukkit.getServer().getWorlds()) {
			for(Entity e : world.getEntities()) {
				if(e.getUniqueId().equals(uniqueId)) {
					return e;
				}
			}
		}
		return null;
	}
	
	public EntityType getType() {
		if(getEntity() != null) {
			return getEntity().getType();
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "SNEntity [uniqueId=" + uniqueId + "]";
	}
}
