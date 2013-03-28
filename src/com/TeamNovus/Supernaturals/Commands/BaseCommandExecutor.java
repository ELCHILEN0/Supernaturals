package com.TeamNovus.Supernaturals.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Supernaturals.Supernaturals;

public class BaseCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + Supernaturals.getPlugin().getName() + CommandManager.getDarkColor() + " ]______________");
			sender.sendMessage(CommandManager.getDarkColor() + "Description: " + CommandManager.getLightColor() + Supernaturals.getPlugin().getDescription().getDescription());
			sender.sendMessage(CommandManager.getDarkColor() + "Author: " + CommandManager.getLightColor() + Supernaturals.getPlugin().getDescription().getAuthors().get(0));
			sender.sendMessage(CommandManager.getDarkColor() + "Version: " + CommandManager.getLightColor() + Supernaturals.getPlugin().getDescription().getVersion());
			sender.sendMessage(CommandManager.getDarkColor() + "Website: " + CommandManager.getLightColor() + Supernaturals.getPlugin().getDescription().getWebsite());
			sender.sendMessage(CommandManager.getLightColor() + "For help type: " + CommandManager.getDarkColor() + "/" + commandLabel + " help [Page]");
			return true;
		}
		
		if(CommandManager.getCommand(args[0]) == null) {
			sender.sendMessage(ChatColor.RED + "The specified command was not found!");
			return true;
		}
		
		BaseCommand command = CommandManager.getCommand(args[0]);

		if((args.length < command.min()) || (args.length > command.max() && command.max() != -1)) {
			sender.sendMessage(ChatColor.RED + "Usage: /" + commandLabel + " " + command.aliases()[0] + " " + command.usage());
			return true;
		}
		
		CommandManager.dispatchCommand(command, sender, cmd, commandLabel, args);
		return true;
	}
}
