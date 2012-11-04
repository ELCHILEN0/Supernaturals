package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "<>--------------[ " + ChatColor.RED + "SupernaturalRaces" + ChatColor.GOLD  + " ]--------------<>");
		sender.sendMessage(ChatColor.YELLOW + "/races " + ChatColor.AQUA + "View all avaliable races!");
		sender.sendMessage(ChatColor.YELLOW + "/info <Race> " + ChatColor.AQUA + "View a races abilities, skills and powers!");
		sender.sendMessage(ChatColor.YELLOW + "/convert <Race> " + ChatColor.AQUA + "Convert to a Race!");
		sender.sendMessage(ChatColor.YELLOW + "/power" + ChatColor.AQUA + "View your power!");
		return true;
	}
}
