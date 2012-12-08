package com.TeamNovus.SupernaturalRaces.Managers;

import java.util.Iterator;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayers;
import com.TeamNovus.SupernaturalRaces.Character.SNRace;

public class PlayerManager {
	private SNPlayers players = new SNPlayers();
	
	public SNPlayers getPlayers() {
		return players;
	}
	
	public void putPlayer(SNPlayer player) {
		players.set(player);
	}
	
	public void removePlayer(SNPlayer player) {
		players.remove(player);
	}
	
	/**
	 * Get a SNPlayer from a Player
	 */
	public SNPlayer getPlayer(Player player) {
		return getPlayer(player.getName());
	}

	public SNPlayer getPlayer(String name) {
		if(players.get(name) == null) {
			putPlayer(new SNPlayer(name));
		}
		
		return players.get(name);
	}
	
	/**
	 * Get all the players in a race
	 */
	public SNPlayers getPlayersInRace(SNRace race) {
		SNPlayers racePlayers = new SNPlayers();
		
		Iterator<SNPlayer> iterator = players.iterator();
		while(iterator.hasNext()) {
			SNPlayer player = iterator.next();
			if(player.getRace().equals(race.name())) {
				racePlayers.add(player);
			}
		}
		
		return racePlayers;
	}
}
