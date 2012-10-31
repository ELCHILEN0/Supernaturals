package com.TeamNovus.SupernaturalRaces.Managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;

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

	public void save() {
		for(String name : players.keySet()) {
			Integer count = plugin.getDb().resultInt(plugin.getDb().query("SELECT COUNT(*) FROM `sn_players` WHERE `name` = " + name), 0);
			if(count == 0) {
				plugin.getDb().query("INSERT INTO `sn_players` (`name`, `race`, `power`) VALUES (" + name + ", " + players.get(name).getRace() + ", " + players.get(name).getPower() + ")");
			} else {
				plugin.getDb().query("UPDATE `sn_players` WHERE `name` = " + name + " SET `race` = " + players.get(name).getRace() + ", `power` = " + players.get(name).getPower());
			}
		}
	}

	public void load() {
		players.clear();
		ResultSet result = plugin.getDb().query("SELECT * FROM `sn_players`");
		try {
			while(result.next()) {
				SNPlayer player = new SNPlayer();
				player.setRace(result.getString(1));
				player.setPower(result.getInt(2));
				players.put(result.getString(0), player);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
