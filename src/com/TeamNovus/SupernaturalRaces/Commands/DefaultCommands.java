package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.SupernaturalRaces.Util.Util;

public class DefaultCommands {
	
	@BaseCommand(aliases = { "tutorial", "tut" }, description = "Learn about the plugin through an interactive tutorial!", usage = "tutorial [Page]")
	public void onTutorialCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length >= 1 && Util.isInt(args[0])) {
			int page = Util.isInt(args[0]) ? Integer.valueOf(args[0]) : 0;
			if(page == 1) {
				sender.sendMessage(ChatColor.AQUA + "Page 1");
				
			} else if(page == 2) {
				sender.sendMessage(ChatColor.AQUA + "Page 2");
				
			} else if(page == 3) {
				sender.sendMessage(ChatColor.AQUA + "Page 3");
				
			} else {
				sender.sendMessage(ChatColor.AQUA + "Default Tutorial Page");
			}
		}
	}
	
	@BaseCommand(aliases = { "help", "?" }, description = "Learn about the commands and their usage!", usage = "help [Page]")
	public void onHelpCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length >= 1 && Util.isInt(args[0])) {
			int page = Util.isInt(args[0]) ? Integer.valueOf(args[0]) : 0;
			if(page == 1) {
				sender.sendMessage(ChatColor.AQUA + "Page 1");
				
			} else if(page == 2) {
				sender.sendMessage(ChatColor.AQUA + "Page 2");

			} else if(page == 3) {
				sender.sendMessage(ChatColor.AQUA + "Page 3");

			} else {
				sender.sendMessage(ChatColor.AQUA + "Default Help Page");
			}
		}
	}

}
