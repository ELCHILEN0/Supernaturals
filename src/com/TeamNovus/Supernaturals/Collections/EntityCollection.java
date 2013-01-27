package com.TeamNovus.Supernaturals.Collections;

import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class EntityCollection<E extends Entity> {
	private Integer nextId = 0;
	protected LinkedHashSet<E> entities = new LinkedHashSet<E>(); 
	
	private Boolean isIdFree(Integer id) {
		HashSet<Integer> ids = new HashSet<Integer>();
		for (E e : entities) {
			ids.add(e.getId());
		}
		
		return !(ids.contains(id));
	}
	
	protected Integer getNextId() {
		while (!(isIdFree(nextId))) {
			nextId++;
		}
		
		return nextId;
	}
}
