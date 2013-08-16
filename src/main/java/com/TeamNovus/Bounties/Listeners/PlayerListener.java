package com.TeamNovus.Bounties.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.TeamNovus.Bounties.BPlayers;
import com.TeamNovus.Bounties.Bounties;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent event) {		
		Bukkit.getServer().getScheduler().runTaskAsynchronously(Bounties.getPlugin(), new Runnable() {
			
			public void run() {
				BPlayers.i.get(event.getPlayer());
			}
		});
	}

}
