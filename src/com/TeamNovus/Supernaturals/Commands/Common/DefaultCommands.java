package com.TeamNovus.Supernaturals.Commands.Common;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Supernaturals.Permission;
import com.TeamNovus.Supernaturals.Supernaturals;
import com.TeamNovus.Supernaturals.Commands.BaseCommand;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Util.StringUtil;

public class DefaultCommands {

	@BaseCommand(aliases = { "help", "?" }, desc = "View all commands and their info.", usage = "[Page]", permission = Permission.NONE, min = 0, max = 1, hidden = true)
	public void helpCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// help [Page]
		int max = 6;
		
		if(args.length != 0) {
			if(!(StringUtil.isInteger(args[0])) || Math.abs(Integer.valueOf(args[0])) * max - max >= CommandManager.getCommands().size()) {
				sender.sendMessage(ChatColor.RED + "The specified page was not found.");
				return;
			}
		}
		
		int page = args.length == 0 ? 1 : Math.abs(Integer.valueOf(args[0]));
		int total = 0;
		sender.sendMessage(CommandManager.getExtra() + "__________________.[ " + CommandManager.getHighlight() + Supernaturals.getPlugin().getName() + CommandManager.getExtra() + " ].__________________");

		sender.sendMessage(ChatColor.GRAY + "Required: < > Optional: [ ]");
		for (int i = max * page - max; i < CommandManager.getCommands().size() && total < max - 1; i++) {
			BaseCommand command = CommandManager.getCommands().get(i);
			if(!(command.hidden()) && Permission.has(command.permission(), sender)) {
				sender.sendMessage(CommandManager.getExtra() + " - " + CommandManager.getDark() + "/" + commandLabel + " " + command.aliases()[0] + (!(command.usage().equals("")) ? " " + command.usage() : "") + ": " + CommandManager.getLight() + command.desc());
				total++;
			}
		}
		sender.sendMessage(CommandManager.getLight() + "For help type: " + CommandManager.getHighlight() + "/" + commandLabel + " help [Page]");
		sender.sendMessage(CommandManager.getExtra() + "---------------------------------------------------");
	}
	
}
