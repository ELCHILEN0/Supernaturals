package com.TeamNovus.Supernaturals.Collections;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class SNPlayerCollection extends EntityCollection<SNPlayer> {
	
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
		if (p.getId() == null || p.getName() == null)
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
