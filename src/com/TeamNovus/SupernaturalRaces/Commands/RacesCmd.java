package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;

public class RacesCmd implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage(ChatColor.BLUE + "Availiable Races:");
		for(SNRace race : SupernaturalRaces.getRaceManager().getRaces()) {
			sender.sendMessage(ChatColor.GOLD + race.name());
		}
		sender.sendMessage(ChatColor.BLUE + "To join a race type " + ChatColor.YELLOW + "/convert <Race>" + ChatColor.BLUE + "!");
		return true;
	}
}
