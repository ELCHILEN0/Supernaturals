package com.TeamNovus.SupernaturalRaces.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class ServerListener implements Listener {
	
	@EventHandler
	public void onPluginEnable(PluginEnableEvent event) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			SupernaturalRaces.getEntityManager().addRaceEffects(p);
		}
	}
	
}
