package com.TeamNovus.SupernaturalRaces.Tasks;

import org.bukkit.Bukkit;

import com.TeamNovus.SupernaturalRaces.Events.ServerTickEvent;

public class ServerTickTask implements Runnable {
	
	@Override
	public void run() {
		Bukkit.getPluginManager().callEvent(new ServerTickEvent());
	}
	
}
