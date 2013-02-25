package com.TeamNovus.Supernaturals.Collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class SNPlayerCollection {
	private LinkedHashSet<SNPlayer> entities = new LinkedHashSet<SNPlayer>();
	
	private Integer currentId = 0;

	private Boolean isIdFree(Integer id) {
		HashSet<Integer> ids = new HashSet<Integer>();
		for (SNPlayer e : entities) {
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
	
	public LinkedHashSet<SNPlayer> getAllPlayers() {
		return entities;
	}
	
	public ArrayList<SNPlayer> getOnlinePlayers() {
		ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			players.add(get(p));
		}
		
		return players;
	}
	
	public ArrayList<SNPlayer> getPlayersInClass(SNClass playerClass) {
		ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();
		
		for (SNPlayer p : entities) {
			if (p.getPlayerClass().getName().equals(playerClass.getName())) {
				players.add(p);
			}
		}
		
		return players;
	}
	
	public ArrayList<SNPlayer> getOnlinePlayersInClass(SNClass race) {
		ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();
		
		for (SNPlayer p : entities) {
			if (p.isOnline() && p.getPlayerClass().equals(race.getName())) {
				players.add(p);
			}
		}
		
		return players;
	}
	
	public SNPlayer getPlayer(String s) {
		for(SNPlayer p : getOnlinePlayers()) {
			if(s.toLowerCase().startsWith(p.getName().toLowerCase())) {
				return p;
			}
		}
		
		return null;
	}
	
	public SNPlayer getPlayerExact(String s) {
		for(SNPlayer p : getOnlinePlayers()) {
			if(s.equals(p.getName())) {
				return p;
			}
		}
		
		return null;
	}
	
	public SNPlayer get(Player p) {
		for (SNPlayer e : entities) {			
			if (e.getName().equals(p.getName())) {
				return e;
			}
		}
		
		SNPlayer e = new SNPlayer(p);
		e.setId(getNextId());
		entities.add(e);
		return e;
	}
	
	public Boolean attached(SNPlayer p) {
		if (p.getName() == null)
			return false;
		
		return entities.contains(p);
	}
	
	public Boolean detached(SNPlayer p) {
		return !(attached(p));
	}
	
	public void attach(SNPlayer p) {		
		entities.add(p);
	}
	
	public void detach(SNPlayer p) {
		entities.remove(p);
	}
	
	public void update(SNPlayer p) {
		entities.add(p);
	}
	
}
