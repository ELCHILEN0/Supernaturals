package com.TeamNovus.SupernaturalRaces.Tasks;

import org.bukkit.Bukkit;
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
				if(race.equals(new DemonRace())) {
					if(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL)) {
						regenPower(player, 1.5);
					} else if(p.getWorld().getTime() > 12000) {
						regenPower(player, 1.0);
					}
				}

				if(race.equals(new AngelRace())) {
					if(p.getWorld().getTime() < 12000) {
						regenPower(player, 2.0);
					}
				}
				
				if(race.equals(new PriestRace())) {
					regenPower(player, 1.25);
				}
				
				if(race.equals(new VampireRace())) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(player, 2.5);
					}
				}
				
				if(race.equals(new WerewolfRace())) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(player, 2.5);
					}
				}
			}
		}			
	}
	
	public void regenPower(SNPlayer player, Double enhancement) {
		Race race = SupernaturalRaces.getRaceManager().getRace(player);

		if(player.getPower() + race.powerIncrement() > race.maxPower()) {
			player.setPower(player.getPower() + (race.maxPower() - player.getPower()));					
		} else { 
			player.setPower((int) (player.getPower() + race.powerIncrement()*enhancement));
		}
	}

}
