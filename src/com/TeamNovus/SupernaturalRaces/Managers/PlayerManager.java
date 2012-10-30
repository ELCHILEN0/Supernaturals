package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Races.AngelRace;
import com.TeamNovus.SupernaturalRaces.Races.PriestRace;

public class PlayerManager {
	private SupernaturalRaces plugin;
	private HashMap<String, SNPlayer> players = new HashMap<String, SNPlayer>();
	private List<Race> races = new ArrayList<Race>();
	
	public PlayerManager(SupernaturalRaces plugin) {
		this.plugin = plugin;
		this.plugin.getDataFolder();
		registerRaces();
	}
	
	/**
	 * This registers any custom races.
	 * TODO: Search the build-path and a specified folder.
	 */
	public void registerRaces() {
		races.add(new PriestRace());
		races.add(new AngelRace());
	}
	
	public HashMap<String, SNPlayer> getPlayers() {
		return players;
	}
	
	public HashMap<String, SNPlayer> getPlayersInRace(String race) {
		HashMap<String, SNPlayer> racePlayers = new HashMap<String, SNPlayer>();
		for(String player : players.keySet()) {
			if(players.get(player).getRace().equalsIgnoreCase(race)) {
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
	
	public Race getRace(SNPlayer player) {
		for(Race race : races) {
			if(player.getRace().equalsIgnoreCase(race.name()))
				return race;
		}
		return new AngelRace();
	}
}
