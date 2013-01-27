package com.TeamNovus.Supernaturals.Collections;

import java.util.LinkedHashSet;

import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Entity.SNEntity;

public class SNEntityCollection extends EntityCollection<SNEntity> {
	
	public SNEntity get(LivingEntity entity) {
		for (SNEntity e : entities) {			
			if (e.getUUID().equals(entity.getUniqueId())) {
				return e;
			}
		}
		
		SNEntity e = new SNEntity(entity);
		e.setId(getNextId());
		entities.add(e);
		return e;
	}
	
	public LinkedHashSet<SNEntity> getEntites() {
		return entities;
	}
	
	public Boolean attached(SNEntity e) {
		if (e.getId() == null || e.getUUID() == null)
			return false;
		
		return entities.contains(e);
	}
	
	public Boolean detached(SNEntity e) {
		return !(attached(e));
	}
	
	public void attach(SNEntity e) {		
		entities.add(e);
	}
	
	public void detach(SNEntity e) {
		entities.remove(e);
	}
	
	public void update(SNEntity e) {
		entities.add(e);
	}
	
}
