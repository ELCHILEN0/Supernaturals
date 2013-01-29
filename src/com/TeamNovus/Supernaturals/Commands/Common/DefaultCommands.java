package com.TeamNovus.Supernaturals.Commands.Common;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Commands.BaseCommand;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Util.StringUtil;

public class DefaultCommands {

	@BaseCommand(aliases = { "help", "?" }, description = "View all commands and their info.", usage = "[Page]", min = 1, max = 2, hidden = true)
	public void helpCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// help [Page]
		int max = 6;
		
		if(args.length != 1) {
			if(!(StringUtil.isInteger(args[1])) || Math.abs(Integer.valueOf(args[1])) * max - max >= CommandManager.getCommands().size()) {
				sender.sendMessage(ChatColor.RED + "The specified page was not found.");
				return;
			}
		}
		
		int page = args.length == 1 ? 1 : Math.abs(Integer.valueOf(args[1]));
		int total = 0;
		sender.sendMessage(ChatColor.GOLD + "<>---------------[ " + ChatColor.DARK_RED + Supernaturals.getPlugin().getName() + ChatColor.GOLD + " ]---------------<>");
		sender.sendMessage(ChatColor.GRAY + "Required: < > Optional: [ ]");
		for (int i = max * page - max; i < CommandManager.getCommands().size() && total < max - 1; i++) {
			BaseCommand command = CommandManager.getCommands().get(i);
			if(!(command.hidden())) {
				sender.sendMessage(ChatColor.GOLD + "- " + "/" + commandLabel + " " + command.aliases()[0] + (command.usage() != "" ? " " + command.usage() : "") + ChatColor.DARK_RED + " - " + ChatColor.GOLD + command.description());
				total++;
			}
		}
		sender.sendMessage(ChatColor.DARK_RED + "For help type: " + ChatColor.GOLD + "/" + commandLabel + " help [Page]");
	}
	
}
