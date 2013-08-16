package com.TeamNovus.Bounties.Commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CommandExecutor implements org.bukkit.command.CommandExecutor, TabCompleter {

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		Command cmd = null;
				
		String[] labels = (String[]) ArrayUtils.add(args, 0, label);
		
		System.out.println("All Labels: " + StringUtils.join(labels, ", "));
		System.out.println("Args: " + StringUtils.join(Commands.getArgs(labels), ", "));
		
		cmd = Commands.getCommand(labels);
		
		if (cmd == null) {
			sender.sendMessage(ChatColor.RED + "The specified command was not found!");
			
			return true;
		}
		
		if (cmd.doesntAllowConsole() && sender instanceof ConsoleCommandSender) {
			sender.sendMessage(cmd.getConsoleDisallowedMessage());
			
			return true;
		}
		
		if (cmd.doesntAllowPlayer() && sender instanceof Player) {
			sender.sendMessage(cmd.getPlayerDisallowedMessage());
			
			return true;
		}
		
//		if (trueArgs.length < cmd.getMinArgs() || trueArgs.length > cmd.getMaxArgs()) {
//			sender.sendMessage(cmd.getUsageMessage());
//			
//			return true;
//		}
				
		cmd.onCommand(labels, sender, args);
		
		return true;
	}

	public List<String> onTabComplete(CommandSender arg0, org.bukkit.command.Command command, String arg2, String[] arg3) {
		
		
		return new ArrayList<String>();
	}


}
