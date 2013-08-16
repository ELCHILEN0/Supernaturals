package com.TeamNovus.Bounties.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Bounties.Player.BPlayer;

public class BPlayerCollection {
	private HashMap<String, BPlayer> players = new HashMap<String, BPlayer>();
	
	public Collection<BPlayer> getAllPlayers() {
		return players.values();
	}
	
	public ArrayList<BPlayer> getOnlinePlayers() {
		ArrayList<BPlayer> players = new ArrayList<BPlayer>();
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			players.add(get(p));
		}
		
		return players;
	}
	
	public BPlayer getPlayer(String name) {
		for(BPlayer player : getOnlinePlayers()) {
			if(player.getName().toLowerCase().startsWith(name.toLowerCase())) {
				return player;
			}
		}
		
		return null;
	}
	
	public BPlayer getPlayerExact(String name) {
		for(BPlayer player : getOnlinePlayers()) {
			if(player.getName().equals(name)) {
				return player;
			}
		}
		
		return null;
	}
	
	public BPlayer get(Player player) {
		if(!(players.containsKey(player.getName()))) {
			return attach(new BPlayer(player));
		}
		
		return get(player.getName());
	}

	public BPlayer get(String name) {
		return players.get(name);
	}
	
	public BPlayer attach(BPlayer p) {		
		return players.put(p.getName(), p);
	}
	
	public void detach(BPlayer p) {
		players.remove(p);
	}
	
	public boolean attached(Player p) {
		return attached(new BPlayer(p));
	}
	
	public boolean attached(BPlayer p) {
		if (p.getName() == null)
			return false;
		
		return players.containsKey(p.getName());
	}
	
	public boolean detached(BPlayer p) {
		return !(attached(p));
	}
	
}
