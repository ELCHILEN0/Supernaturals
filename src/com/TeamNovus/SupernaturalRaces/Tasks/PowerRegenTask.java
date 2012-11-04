package com.TeamNovus.SupernaturalRaces.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;

public class PowerRegenTask implements Runnable {
	@Override
	public void run() {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			SNPlayer p = SupernaturalRaces.getPlayerManager().getPlayer(player);
			SNRace r = SupernaturalRaces.getRaceManager().getRace(p);
			if(p.getPower() < r.maxPower() && r.powerIncrement() != 0) {
				if(p.getPower() + r.powerIncrement() > r.maxPower()) {
					p.setPower(p.getPower() + (r.maxPower() - p.getPower()));
				} else {
					p.setPower(p.getPower() + r.powerIncrement());
				}
			}
		}			
	}

}
