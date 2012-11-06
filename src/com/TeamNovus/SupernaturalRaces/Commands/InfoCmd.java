package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class InfoCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /sninfo <Race>");
			return true;
		} else if(args.length == 1) {
			if(!(sender.hasPermission("supernaturalraces.info"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
				return false;
			}
			
			for(Race race : SupernaturalRaces.getRaceManager().getRaces()) {
				if(args[0].equalsIgnoreCase(race.name())) {
					sender.sendMessage(ChatColor.GOLD + race.name());
					sender.sendMessage(ChatColor.BLUE + "  Max Power: " + ChatColor.YELLOW + race.maxPower());
					sender.sendMessage(ChatColor.BLUE + "  Power Increment: " + ChatColor.YELLOW + race.powerIncrement());
					for(Spell spell : race.spells()) {
						sender.sendMessage(ChatColor.GOLD + "  " + spell.name());
						sender.sendMessage(ChatColor.BLUE + "    Info: " + ChatColor.YELLOW + spell.info());
						sender.sendMessage(ChatColor.BLUE + "    Bound to: " + ChatColor.YELLOW + spell.binding().name());
						sender.sendMessage(ChatColor.BLUE + "    Reagents: " + ChatColor.YELLOW + spell.required().toString());
						sender.sendMessage(ChatColor.BLUE + "    Consumes: " + ChatColor.YELLOW + spell.consume().toString());
					}
				}
			}
			return true;
		}
		return false;
	}
}
