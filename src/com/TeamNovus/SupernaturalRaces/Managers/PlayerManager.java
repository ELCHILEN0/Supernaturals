package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;

public class PlayerManager {
	private HashMap<String, SNPlayer> players = new HashMap<String, SNPlayer>();

	public HashMap<String, SNPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(HashMap<String, SNPlayer> players) {
		this.players.clear();
		for(String key : players.keySet()) {
			System.out.println(key);
			this.players.put(key, players.get(key));
		}
	}
	
	/**
	 * Get a SNPlayer from a Player
	 */
	public SNPlayer getPlayer(Player player) {
		return getPlayer(player.getName());
	}

	public SNPlayer getPlayer(String name) {
		if(!(players.containsKey(name))) {
			players.put(name, new SNPlayer());
		}
		return players.get(name);
	}

	/**
	 * Get all the players in a race
	 */
	public HashMap<String, SNPlayer> getPlayersInRace(SNRace race) {
		HashMap<String, SNPlayer> racePlayers = new HashMap<String, SNPlayer>();
		for(String player : players.keySet()) {
			if(players.get(player).getRace().equalsIgnoreCase(race.name())) {
				racePlayers.put(player, players.get(player));
			}
		}
		return racePlayers;
	}
}
