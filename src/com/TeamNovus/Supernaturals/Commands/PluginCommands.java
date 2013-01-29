package com.TeamNovus.Supernaturals.Commands;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Permission;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNClass;
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
		if(!(Permission.has(Permission.COMMAND_ONLINE, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		ArrayList<SNClass> classes = new ArrayList<SNClass>();
		
		for(SNPlayer player : SNPlayers.i.getOnlinePlayers()) {
			if(!(classes.contains(player.getPlayerClass()))) {
				classes.add(player.getPlayerClass());
			}
		}
		
		ArrayList<String> lines = new ArrayList<String>();
		
		for(SNClass playerClass : classes) {
			
			ArrayList<String> names = new ArrayList<String>();
			
			for(SNPlayer player : SNPlayers.i.getPlayersInClass(playerClass)) {
				names.add(player.getName());
			}
			
			lines.add(playerClass.getColor() + playerClass.getName() + ": " + StringUtils.join(names, ", "));
		
		}
		
		for(String line : lines) {
			sender.sendMessage(line);
		}
	}
	
	@BaseCommand(aliases = { "info" }, description = "View information on online players.", usage = "[Player]", min = 1, max = 2)
	public void onInfoCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 1 && Permission.has(Permission.COMMAND_INFO, sender)) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			}
			
			SNPlayer player = SNPlayers.i.get((Player) sender);
			
			sender.sendMessage(ChatColor.DARK_RED + "<>-------------------------<>");
			
			sender.sendMessage(ChatColor.GOLD + "  Name: " + ChatColor.RESET + player.getName());
			sender.sendMessage(ChatColor.GOLD + "  Class: " + ChatColor.RESET + player.getPlayerClass().getName());
			
			if(player.getPlayerClass().getParentClass() != null)
				sender.sendMessage(ChatColor.GOLD + "  Parent Class: " + ChatColor.RESET + player.getPlayerClass().getParentClass().getColor() + player.getPlayerClass().getParentClass().getName());
			
			ArrayList<String> classes = new ArrayList<String>();
			
			for(SNClass c : player.getJoinableClasses()) {
				classes.add(c.getColor() + c.getName());
			}
			
			if(classes.size() > 0)
				sender.sendMessage(ChatColor.GOLD + "  Sub-Classes: " + ChatColor.RESET + StringUtils.join(classes, ", "));
			
			sender.sendMessage(ChatColor.GOLD + "  Mana: " + ChatColor.RESET + player.getMana() + "/" + player.getMaxMana());
			sender.sendMessage(ChatColor.GOLD + "  Speed: " + ChatColor.RESET + player.getSpeed());
			sender.sendMessage(ChatColor.GOLD + "  Health: " + ChatColor.RESET + player.getHealth() + "/" + player.getMaxHealth());
			sender.sendMessage(ChatColor.GOLD + "  Food Level: " + ChatColor.RESET + player.getFoodLevel() + "/" + player.getMaxFoodLevel());
			
			player.sendMessage(ChatColor.DARK_RED + "<>-------------------------<>");
		} else if(args.length == 2 && Permission.has(Permission.COMMAND_INFO_OTHERS, sender)) {
			boolean found = false;
			
			for(SNPlayer player : SNPlayers.i.getOnlinePlayers()) {
				if(player.getName().startsWith(args[1]) && !(found)) {
					found = true;
					
					sender.sendMessage(ChatColor.DARK_RED + "<>-------------------------<>");
					
					sender.sendMessage(ChatColor.GOLD + "  Name: " + ChatColor.RESET + player.getName());
					sender.sendMessage(ChatColor.GOLD + "  Class: " + ChatColor.RESET + player.getPlayerClass().getName());
					
					if(player.getPlayerClass().getParentClass() != null)
						sender.sendMessage(ChatColor.GOLD + "  Parent Class: " + ChatColor.RESET + player.getPlayerClass().getParentClass().getColor() + player.getPlayerClass().getParentClass().getName());
					
					ArrayList<String> classes = new ArrayList<String>();
					
					for(SNClass c : player.getJoinableClasses()) {
						classes.add(c.getColor() + c.getName());
					}
					
					if(classes.size() > 0)
						sender.sendMessage(ChatColor.GOLD + "  Sub-Classes: " + ChatColor.RESET + StringUtils.join(classes, ", "));
					
					sender.sendMessage(ChatColor.GOLD + "  Mana: " + ChatColor.RESET + player.getMana() + "/" + player.getMaxMana());
					sender.sendMessage(ChatColor.GOLD + "  Speed: " + ChatColor.RESET + player.getSpeed());
					sender.sendMessage(ChatColor.GOLD + "  Health: " + ChatColor.RESET + player.getHealth() + "/" + player.getMaxHealth());
					sender.sendMessage(ChatColor.GOLD + "  Food Level: " + ChatColor.RESET + player.getFoodLevel() + "/" + player.getMaxFoodLevel());
					sender.sendMessage(ChatColor.DARK_RED + "<>-------------------------<>");
				}
				
				if(!(found)) {
					sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		}
	}
	
}
