package com.TeamNovus.Supernaturals.Collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Entity.SNEntity;

public class SNEntityCollection {
	private HashMap<UUID, SNEntity> entities = new HashMap<UUID, SNEntity>();

	public Collection<SNEntity> getEntites() {
		return entities.values();
	}
	
	public SNEntity get(LivingEntity entity) {
		if(!(entities.containsKey(entity.getUniqueId()))) {
			return attach(new SNEntity(entity));
		}
		
		return entities.get(entity.getUniqueId());
	}
	
	public SNEntity attach(SNEntity e) {
		return entities.put(e.getUUID(), e);
	}
	
	public void detach(SNEntity e) {
		entities.remove(e);
	}

	public boolean attached(LivingEntity e) {
		return attached(new SNEntity(e));
	}
	
	public boolean attached(SNEntity e) {
		if (e.getUUID() == null)
			return false;
		
		return entities.containsKey(e.getUUID());
	}
	
	public boolean detached(SNEntity e) {
		return !(attached(e));
	}
	
}
