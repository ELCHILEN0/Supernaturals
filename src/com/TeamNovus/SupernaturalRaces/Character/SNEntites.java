package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.entity.Entity;

public class SNEntites {
	private ArrayList<SNEntity> entities = new ArrayList<SNEntity>();

	public boolean add(SNEntity e) {
		return entities.add(e);
	}
	
	public boolean addAll(Collection<? extends SNEntity> c) {
		return entities.addAll(c);
	}
	
	public boolean put(SNEntity e) {
		if(contains(e)) {
			remove(e);
		}
		return add(e);
	}
	
	public void clear() {
		entities.clear();
	}

	public boolean contains(Object o) {
		return entities.contains(o);
	}
	
	public boolean contains(SNEntity e) {
		Iterator<SNEntity> iterator = entities.iterator();
		
		while(iterator.hasNext()) {
			SNEntity entity = iterator.next();
			if(entity.getUniqueId().equals(e.getUniqueId())) {
				return true;
			}
		}
		return false;
	}
	
	public SNEntity get(Entity e) {
		Iterator<SNEntity> iterator = entities.iterator();
		
		while(iterator.hasNext()) {
			SNEntity entity = iterator.next();
			if(entity.getUniqueId().equals(e.getUniqueId())) {
				return entity;
			}
		}
		
		return null;
	}

	public boolean containsAll(Collection<?> c) {
		return entities.containsAll(c);
	}

	public boolean isEmpty() {
		return entities.isEmpty();
	}

	public Iterator<SNEntity> iterator() {
		return entities.iterator();
	}

	public boolean remove(Object o) {
		return entities.remove(o);
	}
	
	public boolean remove(SNEntity e) {
		Iterator<SNEntity> iterator = entities.iterator();
		
		while(iterator.hasNext()) {
			SNEntity entity = iterator.next();
			if(entity.getUniqueId().equals(e.getUniqueId())) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public int size() {
		return entities.size();
	}
}
