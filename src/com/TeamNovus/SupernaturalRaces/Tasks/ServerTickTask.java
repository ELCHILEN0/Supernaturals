package com.TeamNovus.SupernaturalRaces.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Events.PlayerTickEvent;
import com.TeamNovus.SupernaturalRaces.Listeners.DefaultServerListener;

public class ServerTickTask implements Runnable {
	
	@Override
	public void run() {
		new DefaultServerListener().onServerTick();
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			Bukkit.getPluginManager().callEvent(new PlayerTickEvent(p));
		}
	}
	
}
