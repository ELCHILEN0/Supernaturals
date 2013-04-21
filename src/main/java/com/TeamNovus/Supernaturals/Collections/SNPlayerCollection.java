package com.TeamNovus.Supernaturals.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class SNPlayerCollection {
	private HashMap<String, SNPlayer> players = new HashMap<String, SNPlayer>();
	
	public Collection<SNPlayer> getAllPlayers() {
		return players.values();
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
		
		for (SNPlayer p : getAllPlayers()) {
			if (p.getPlayerClass().getName().equals(playerClass.getName())) {
				players.add(p);
			}
		}
		
		return players;
	}
	
	public ArrayList<SNPlayer> getOnlinePlayersInClass(SNClass playerClass) {
		ArrayList<SNPlayer> players = new ArrayList<SNPlayer>();
		
		for (SNPlayer p : getOnlinePlayers()) {
			if (p.getPlayerClass().equals(playerClass.getName())) {
				players.add(p);
			}
		}
		
		return players;
	}
	
	public SNPlayer getPlayer(String name) {
		for(SNPlayer player : getOnlinePlayers()) {
			if(player.getName().startsWith(name.toLowerCase())) {
				return player;
			}
		}
		
		return null;
	}
	
	public SNPlayer getPlayerExact(String name) {
		for(SNPlayer player : getOnlinePlayers()) {
			if(player.getName().equals(name)) {
				return player;
			}
		}
		
		return null;
	}
	
	public SNPlayer get(Player player) {
		if(!(players.containsKey(player.getName()))) {
			return attach(new SNPlayer(player));
		}
		
		return get(player.getName());
	}

	public SNPlayer get(String name) {
		return players.get(name);
	}
	
	public Boolean attached(SNPlayer p) {
		if (p.getName() == null)
			return false;
		
		return players.containsKey(p.getName());
	}
	
	public Boolean detached(SNPlayer p) {
		return !(attached(p));
	}
	
	public SNPlayer attach(SNPlayer p) {		
		return players.put(p.getName(), p);
	}
	
	public void detach(SNPlayer p) {
		players.remove(p);
	}
	
}
