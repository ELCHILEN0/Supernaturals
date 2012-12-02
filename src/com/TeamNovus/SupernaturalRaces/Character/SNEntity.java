package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect.SNEffectType;

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
	
	public void putEffect(SNEffect effect) {
		removeEffect(effect.getType());
		effects.add(effect);
	}
	
	public void addEffects(ArrayList<SNEffect> effects) {
		for(SNEffect effect : effects) {
			putEffect(effect);
		}
	}
	
	public void removeEffect(SNEffectType type) {
		Iterator<SNEffect> iterator = effects.iterator();
		while(iterator.hasNext()) {
			SNEffect effect = iterator.next();
			if(effect.getType().equals(type)) {
				iterator.remove();
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
