package com.TeamNovus.SupernaturalRaces.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Race.Angel.AngelRace;
import com.TeamNovus.SupernaturalRaces.Race.Demon.DemonRace;
import com.TeamNovus.SupernaturalRaces.Race.Mage.MageRace;
import com.TeamNovus.SupernaturalRaces.Race.Necromancer.NecromancerRace;
import com.TeamNovus.SupernaturalRaces.Race.Priest.PriestRace;
import com.TeamNovus.SupernaturalRaces.Race.Vampire.VampireRace;
import com.TeamNovus.SupernaturalRaces.Race.Werewolf.WerewolfRace;

public class PowerRegenTask implements Runnable {
	@Override
	public void run() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(p);
			Race race = SupernaturalRaces.getRaceManager().getRace(player);
						
			if(player.getPower() < race.maxPower() && race.maxPower() != 0) {
				if(race instanceof AngelRace) {
					if(p.getWorld().getTime() < 12000) {
						regenPower(p, 20);
					} else {
						regenPower(p, 5);
					}
				}
				
				if(race instanceof DemonRace) {
					if(p.getWorld().getBiome(p.getLocation().getBlockX(), p.getLocation().getBlockZ()).equals(Biome.HELL)) {
						regenPower(p, 20);
					} else if(p.getLocation().getBlock().getType().equals(Material.LAVA)) {
						regenPower(p, 10);
					}
				}
				
				if(race instanceof PriestRace) {
					regenPower(p, 15);
				}
				
				if(race instanceof VampireRace) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(p, 15);
					} else {
						regenPower(p, 5);
					}
				}
				
				if(race instanceof WerewolfRace) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(p, 15);
					} else {
						regenPower(p, 5);
					}
				}
				
				if(race instanceof MageRace) {
					if(p.getLocation().getBlock().getRelative(BlockFace.UP).getType().equals(Material.WATER)) {
						regenPower(p, 15);
					} else {
						regenPower(p, 10);
					}
				}
				
				if(race instanceof NecromancerRace) {
					if(p.getWorld().getTime() > 12000) {
						regenPower(p, 20);
					}
				}
			}
		}			
	}
	
	public void regenPower(Player p, Integer amount) {
		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(p);
		Race race = SupernaturalRaces.getRaceManager().getRace(player);
		
		if(player.getPower() < race.maxPower()) {
			if(player.getPower() + amount > race.maxPower()) {
				player.setPower(player.getPower() + (race.maxPower() - player.getPower()));					
			} else { 
				player.setPower(player.getPower() + amount);
			}
			p.sendMessage(ChatColor.YELLOW + "+ " + amount + " Power");
		}
	}

}
