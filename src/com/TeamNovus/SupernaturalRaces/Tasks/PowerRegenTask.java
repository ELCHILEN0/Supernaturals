package com.TeamNovus.SupernaturalRaces.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Race.Angel.AngelRace;
import com.TeamNovus.SupernaturalRaces.Race.Demon.DemonRace;
import com.TeamNovus.SupernaturalRaces.Race.Priest.PriestRace;
import com.TeamNovus.SupernaturalRaces.Race.Vampire.VampireRace;
import com.TeamNovus.SupernaturalRaces.Race.Werewolf.WerewolfRace;

public class PowerRegenTask implements Runnable {
	@Override
	public void run() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(p);
			Race race = SupernaturalRaces.getRaceManager().getRace(player);
						
			if(player.getPower() < race.maxPower() && race.powerIncrement() != 0) {
				if(race instanceof DemonRace) {
					if(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL)) {
						regenPower(p, 1.5);
					} else if(p.getWorld().getTime() > 12000) {
						regenPower(p, 1.0);
					}
				}

				if(race instanceof AngelRace) {
					if(p.getWorld().getTime() < 12000) {
						regenPower(p, 2.0);
					}
				}
				
				if(race instanceof PriestRace) {
					regenPower(p, 1.25);
				}
				
				if(race instanceof VampireRace) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(p, 2.5);
					}
				}
				
				if(race instanceof WerewolfRace) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(p, 2.5);
					}
				}
			}
		}			
	}
	
	public void regenPower(Player p, Double enhancement) {
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(p);
		Race race = SupernaturalRaces.getRaceManager().getRace(player);
		Integer increment = (int) (race.powerIncrement() * enhancement);

		if(player.getPower() < race.maxPower()) {
			if(player.getPower() + increment > race.maxPower()) {
				player.setPower(player.getPower() + (race.maxPower() - player.getPower()));					
			} else { 
				player.setPower(player.getPower() + increment);
			}
			p.sendMessage(ChatColor.YELLOW + "+ " + increment + " Power");
		}
	}

}
