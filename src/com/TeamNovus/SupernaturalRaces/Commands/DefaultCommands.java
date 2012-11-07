package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DefaultCommands {
	
	@BaseCommand(aliases = { "tutorial", "tut" }, description = "Learn about the plugin through an interactive tutorial!", usage = "tutorial [Page]")
	public void onTutorialCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage("Tutorial!");
	}
}
