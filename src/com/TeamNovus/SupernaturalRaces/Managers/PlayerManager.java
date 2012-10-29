package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;

public class PlayerManager {
	private SupernaturalRaces plugin;
	private HashMap<String, SNPlayer> players = new HashMap<String, SNPlayer>();
	
	public PlayerManager(SupernaturalRaces plugin) {
		this.plugin = plugin;
		this.plugin.getDataFolder();
	}
	
	public HashMap<String, SNPlayer> getPlayers() {
		return players;
	}
	
	public HashMap<String, SNPlayer> getPlayersInRace(Race race) {
		HashMap<String, SNPlayer> racePlayers = new HashMap<String, SNPlayer>();
		for(String player : players.keySet()) {
			if(players.get(player).getRace().equalsIgnoreCase(race.name())) {
				racePlayers.put(player, players.get(player));
			}
		}
		return racePlayers;
	}
	
	public SNPlayer getPlayer(String name) {
		if(!(players.containsKey(name))) {
			players.put(name, new SNPlayer());
		}
		return players.get(name);
	}
	
	public SNPlayer getPlayer(Player player) {
		return getPlayer(player.getName());
	}
}
