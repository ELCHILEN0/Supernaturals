package com.TeamNovus.SupernaturalRaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;

public class PowerRegenTask implements Runnable {
	private SupernaturalRaces plugin;
	
	public PowerRegenTask(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			SNPlayer p = plugin.getPlayerManager().getPlayer(player);
			SNRace r = plugin.getRaceManager().getRace(p);
			if(p.getPower() < r.maxPower() || r.powerIncrement() != 0) {
				if(p.getPower() + r.powerIncrement() > r.maxPower()) {
					p.setPower(p.getPower() + (r.maxPower() - p.getPower()));
				} else {
					p.setPower(p.getPower() + r.powerIncrement());
				}
				player.sendMessage(ChatColor.YELLOW + "New Power: " + p.getPower() + "/" + r.maxPower());
			}
		}			
	}

}
