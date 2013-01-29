package com.TeamNovus.Supernaturals.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class PluginCommands {
	/* Commands:
	 * 	Default:
	 *  /sn online
	 *  /sn info [Player]
	 *  /sn mana
	 *  /sn speed
	 *  /sn health
	 *  /sn hunger
	 *  /sn level
	 *  /sn classes
	 *  /sn inspect <Class>
	 *  /sn evolve <Class>
	 *  /sn devolve
	 *  /sn reset
	 *  
	 *  Admin:
	 *  /sn mana <Give/Take/Reset> <Player> <Mana>
	 *  /sn exp <Give/Take/Reset> <Player> <Exp>
	 *  /sn set <Player> <Class>
	 *  
	 */
	
	@BaseCommand(aliases = { "online" }, description = "View information on online players.", usage = "")
	public void onOnlineCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String message = "";
		
		for(SNPlayer player : SNPlayers.i.getOnlinePlayers()) {
			message += player.getName() + player.getPlayerClass().getName() + " ";
		}
		
		sender.sendMessage(message);
	}
	
	
	
}
