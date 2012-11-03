package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;

public class ConvertCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /convert <Race> [Player]");
		} else if(args.length == 1) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run as a player!");
				return false;
			}
			
			Player player = (Player) sender;
			SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
			
			for(SNRace race : SupernaturalRaces.getRaceManager().getRaces()) {
				sender.sendMessage(args[0] + ":" + race.name());
				if(args[0].equalsIgnoreCase(race.name()) && !(snp.getRace().equals(race.name()))) {
					snp.setRace(race.name());
					player.sendMessage(ChatColor.GREEN + "You have converted to a " + race.name() + "!");
					return true;
				}
			}
			
			player.sendMessage(ChatColor.RED + "The specified race was not found! Type /races to see all the avliable races!");
			return false;
		} else if(args.length >= 2) {			
			if(Bukkit.getPlayer(args[1]) == null) {
				sender.sendMessage("The specified player is not online!");
				return false;
			}
			
			Player player = (Player) Bukkit.getPlayer(args[1]);
			SNPlayer snp = SupernaturalRaces.getPlayerManager().getPlayer(player);
			
			for(SNRace race : SupernaturalRaces.getRaceManager().getRaces()) {
				if(args[0].equalsIgnoreCase(race.name()) && !(snp.getRace().equals(race.name()))) {
					snp.setRace(race.name());
					player.sendMessage(ChatColor.GREEN + "You have converted to a " + race.name() + "!");
					return true;
				}
			}
			
			player.sendMessage(ChatColor.RED + "The specified race was not found! Type /races to see all the avliable races!");
			return false;
		}
		
		return false;
	}

}