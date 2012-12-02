package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.entity.Player;

public class SNPlayers {
	private ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();

	public boolean add(SNPlayer e) {
		return players.add(e);
	}
	
	public boolean addAll(Collection<? extends SNPlayer> c) {
		return players.addAll(c);
	}
	
	public boolean set(SNPlayer e) {
		if(contains(e)) {
			remove(e);
		}
		return add(e);
	}
	
	public void clear() {
		players.clear();
	}

	public boolean contains(Object o) {
		return players.contains(o);
	}
	
	public boolean contains(SNPlayer e) {
		Iterator<SNPlayer> iterator = iterator();
		
		while(iterator.hasNext()) {
			SNPlayer player = iterator.next();
			if(player.getName().equals(e.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public SNPlayer get(String name) {
		Iterator<SNPlayer> iterator = iterator();
		
		while(iterator.hasNext()) {
			SNPlayer player = iterator.next();
			if(player.getName().equals(name)) {
				return player;
			}
		}
		
		SNPlayer player = new SNPlayer();
		player.setName(name);
		return player;
	}
	
	public SNPlayer get(Player p) {
		Iterator<SNPlayer> iterator = iterator();
		
		while(iterator.hasNext()) {
			SNPlayer player = iterator.next();
			if(player.getName().equals(p.getName())) {
				return player;
			}
		}
		
		SNPlayer player = new SNPlayer();
		player.setName(p.getName());
		return player;
	}

	public boolean containsAll(Collection<?> c) {
		return players.containsAll(c);
	}

	public boolean isEmpty() {
		return players.isEmpty();
	}

	public Iterator<SNPlayer> iterator() {
		return players.iterator();
	}

	public boolean remove(Object o) {
		return players.remove(o);
	}
	
	public boolean remove(SNPlayer e) {
		Iterator<SNPlayer> iterator = iterator();
		
		while(iterator.hasNext()) {
			SNPlayer player = iterator.next();
			if(player.getName().equals(e.getName())) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public int size() {
		return players.size();
	}
}
