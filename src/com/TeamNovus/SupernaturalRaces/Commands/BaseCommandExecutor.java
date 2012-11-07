package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class BaseCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.YELLOW + "<>--------------[ " + ChatColor.AQUA + "SupernaturalRaces" + ChatColor.YELLOW  + " ]--------------<>");
			sender.sendMessage(ChatColor.AQUA + "SupernaturalRaces allows you to become a unique race!");
			sender.sendMessage(ChatColor.AQUA + "Each race has custom spells, effects and abilities!");
			sender.sendMessage(ChatColor.AQUA + "To get started you should familiarize yourself with the plugin!");
			sender.sendMessage(ChatColor.AQUA + "Type " + ChatColor.YELLOW + "/sn tutorial" + ChatColor.AQUA + " to continue!");
			return false;
		}
		
		if(SupernaturalRaces.getCommandManager().getCommand(args[0]) == null) {
			sender.sendMessage("The specified command was not found!");
			return false;
		}
		
		BaseCommand command = SupernaturalRaces.getCommandManager().getCommand(args[0]);

		if((args.length < command.min()) || (args.length > command.max() && command.max() != -1)) {
			sender.sendMessage("/" + commandLabel + " " + command.aliases()[0] + " " + command.usage());
			return false;
		}
		
		SupernaturalRaces.getCommandManager().dispatchCommand(command, sender, cmd, commandLabel, args);
		return true;
	}
}
