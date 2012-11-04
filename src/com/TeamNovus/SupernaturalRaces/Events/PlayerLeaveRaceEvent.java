package com.TeamNovus.SupernaturalRaces.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLeaveRaceEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Location location;
    private Long timestamp;
    
    public PlayerLeaveRaceEvent(Player player) {
    	this.player = player;
    	this.location = player.getLocation();
    	this.timestamp = System.currentTimeMillis();
    }
    
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Location getLocation() {
		return location;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
