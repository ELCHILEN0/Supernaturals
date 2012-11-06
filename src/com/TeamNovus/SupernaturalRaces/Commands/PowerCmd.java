package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Race;

public class PowerCmd implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			if(!(sender.hasPermission("supernaturalraces.power"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
				return false;
			}
			
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command cannot be run from the console!");
				return false;
			}
			
			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer((Player) sender);
			Race race = SupernaturalRaces.getRaceManager().getRace(player);
			sender.sendMessage(ChatColor.YELLOW + "Power: " + player.getPower() + "/" + race.maxPower());
			return true;
		}
		return false;
	}
}
