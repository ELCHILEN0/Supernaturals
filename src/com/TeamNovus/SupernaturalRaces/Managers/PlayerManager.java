package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Race;

public class PlayerManager {
	private HashMap<String, Integer> mappings = new HashMap<String, Integer>();
	private HashMap<Integer, SNPlayer> players = new HashMap<Integer, SNPlayer>();
	
	public HashMap<String, Integer> getMappings() {
		return mappings;
	}
	
	public HashMap<Integer, SNPlayer> getPlayers() {
		return players;
	}
	
	public void setMappings(HashMap<String, Integer> mappings) {
		this.mappings.clear();
		this.mappings.putAll(mappings);
	}

	public void setPlayers(HashMap<Integer, SNPlayer> players) {
		this.players.clear();
		this.players.putAll(players);
	}
	
	public void setPlayers(HashMap<String, Integer> mappings, HashMap<Integer, SNPlayer> players) {
		setMappings(mappings);
		setPlayers(players);
	}
	
	/**
	 * Get a SNPlayer from a Player
	 */
	public SNPlayer getPlayer(Player player) {
		return getPlayer(player.getName());
	}

	public SNPlayer getPlayer(String name) {
		if(!(mappings.containsKey(name))) {
			mappings.put(name, players.size() + 1);
		}
		
		return getPlayer(mappings.get(name));
	}
	
	public SNPlayer getPlayer(Integer id) {
		if(!(players.containsKey(id))) {
			players.put(id, new SNPlayer());
		}
		
		return players.get(id);
	}
	
	public String getName(Integer id) {
	    for (Entry<String, Integer> entry : SupernaturalRaces.getPlayerManager().getMappings().entrySet()) {
	        if (id.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	/**
	 * Get all the players in a race
	 */
	public HashMap<Integer, SNPlayer> getPlayersInRace(Race race) {
		HashMap<Integer, SNPlayer> racePlayers = new HashMap<Integer, SNPlayer>();
		for(Integer id : players.keySet()) {
			if(players.get(id).getRace().equalsIgnoreCase(race.name())) {
				racePlayers.put(id, players.get(id));
			}
		}
		return racePlayers;
	}
}
