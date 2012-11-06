package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Race;

public class ConvertCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /convert <Race> [Player]");
			return true;
		} else if(args.length == 1) {
			if(!(sender.hasPermission("supernaturalraces.convert"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
				return false;
			}
			
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run as a player!");
				return false;
			}
			
			Player player = (Player) sender;
			SNPlayer snplayer = SupernaturalRaces.getPlayerManager().getPlayer(player);
			
			if(attemptConvert(snplayer, args[0])) {
				player.sendMessage(ChatColor.GREEN + "You have converted to a(n) " + SupernaturalRaces.getRaceManager().getRace(snplayer).name() + "!");
				return true;
			}
			
			player.sendMessage(ChatColor.RED + "The specified race was not found! Type /races to see all the avliable races!");
			return false;
		} else if(args.length >= 2) {
			if(!(sender.hasPermission("supernaturalraces.convert.others"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
				return false;
			}
			
			if(Bukkit.getPlayer(args[1]) == null) {
				sender.sendMessage(ChatColor.RED + "The specified player is not online!");
				return false;
			}
			
			Player player = (Player) Bukkit.getPlayer(args[1]);
			SNPlayer snplayer = SupernaturalRaces.getPlayerManager().getPlayer(player);
			
			if(attemptConvert(snplayer, args[0])) {
				player.sendMessage(ChatColor.GREEN + "You have converted " + player.getDisplayName() + " to a(n) " + SupernaturalRaces.getRaceManager().getRace(snplayer).name() + "!");
				return true;
			}
			
			player.sendMessage(ChatColor.RED + "The specified race was not found! Type /races to see all the avliable races!");
			return false;
		}
		return false;
	}
	
	public boolean attemptConvert(SNPlayer player, String race) {
		for(Race r : SupernaturalRaces.getRaceManager().getRaces()) {
			if(race.equalsIgnoreCase(r.name()) && !(player.getRace().equals(r.name()))) {
				player.setRace(r.name());
				return true;
			}
		}
		return false;
	}
}
