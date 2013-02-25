package com.TeamNovus.Supernaturals.Collections;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.bukkit.entity.LivingEntity;

import com.TeamNovus.Supernaturals.Entity.SNEntity;

public class SNEntityCollection {
	private Integer currentId = 0;
	private LinkedHashSet<SNEntity> entities = new LinkedHashSet<SNEntity>();

	private Boolean isIdFree(Integer id) {
		HashSet<Integer> ids = new HashSet<Integer>();
		for (SNEntity e : entities) {
			ids.add(e.getId());
		}

		return !(ids.contains(id));
	}

	public Integer getNextId() {
		while (!(isIdFree(currentId))) {
			currentId++;
		}

		return currentId;
	}	
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
		if (e.getUUID() == null)
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
