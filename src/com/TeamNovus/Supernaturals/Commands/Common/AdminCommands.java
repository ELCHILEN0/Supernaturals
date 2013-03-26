package com.TeamNovus.Supernaturals.Commands.Common;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.TeamNovus.Supernaturals.Permission;
import com.TeamNovus.Supernaturals.SNClasses;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Classes.Human;
import com.TeamNovus.Supernaturals.Commands.BaseCommand;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Util.StringUtil;

public class AdminCommands {
	/*  Admin Commands:
	 *  /sn modmana <Give/Take/Reset> <Player> <Mana>
	 *  /sn modexp <Give/Take/Reset> <Player> <Exp>
	 *  /sn modclass <Player> <Class>
	 *  /sn reset <Player>
	 *  
	 */
	
	@BaseCommand(aliases = { "modmana" }, description = "Change a players mana.", usage = "<Give/Take/Reset> <Player> <Mana>", min = 4, max = 4)
	public void onModManaCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_MODMANA, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		if(!(args[1].equalsIgnoreCase("give")) && !(args[1].equalsIgnoreCase("take")) && !(args[1].equalsIgnoreCase("reset"))) {
			sender.sendMessage(ChatColor.RED + "The specified action does not exist!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[2]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		if(!(StringUtil.isInteger(args[3]))) {
			sender.sendMessage(ChatColor.RED + "The specified value is not an Integer!");
			return;
		}
		
		int mana = Integer.valueOf(args[3]);
		
		if(args[1].equalsIgnoreCase("give")) {
			player.setMana(player.getMana() + mana, false);
			
			player.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana has been given to your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana has been given to " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[1].equalsIgnoreCase("take")) {
			player.setMana(player.getMana() - mana, false);
			
			player.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.RED + " mana has been removed from your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana has been taken from " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[1].equalsIgnoreCase("reset")) {
			player.setMana(mana, false);
			
			player.sendMessage(ChatColor.GREEN + "Your mana has been set to " + ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana.");
			sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "'s mana has been reset to " + ChatColor.YELLOW + mana + ChatColor.GREEN +" mana!"); 
		}
	}
	
	@BaseCommand(aliases = { "modexp" }, description = "Change a players exp.", usage = "<Give/Take/Reset> <Player> <Exp>", min = 4, max = 4)
	public void onModExpCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_MODEXP, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		if(!(args[1].equalsIgnoreCase("give")) && !(args[1].equalsIgnoreCase("take")) && !(args[1].equalsIgnoreCase("reset"))) {
			sender.sendMessage(ChatColor.RED + "The specified action does not exist!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[2]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		if(!(StringUtil.isInteger(args[3]))) {
			sender.sendMessage(ChatColor.RED + "The specified value is not an Integer!");
			return;
		}
		
		int exp = Integer.valueOf(args[3]);
		
		if(args[1].equalsIgnoreCase("give")) {
			player.setExperience(player.getExperience() + exp);
			
			player.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp has been given to your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp has been given to " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[1].equalsIgnoreCase("take")) {
			player.setExperience(player.getExperience() - exp);
			
			player.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.RED + " exp has been removed from your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp has been taken from " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[1].equalsIgnoreCase("reset")) {
			player.setExperience(exp);
			
			player.sendMessage(ChatColor.GREEN + "Your exp has been set to " + ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp.");
			sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "'s exp has been reset to " + ChatColor.YELLOW + exp + ChatColor.GREEN +" exp!"); 
		}
	}
	
	@BaseCommand(aliases = { "modclass" }, description = "Change a players exp.", usage = "<Player> <Class>", min = 3, max = 3)
	public void onModClassCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_MODCLASS, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[1]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		SNClass targetClass = SNClasses.i.getBestClass(args[2]);
		
		if(targetClass == null) {
			sender.sendMessage(ChatColor.RED + "The specified class does not exist!");
			return;
		}
		
		player.setPlayerClass(targetClass, true);
		if(StringUtil.startsWithVowel(targetClass.getName())) {
			player.sendMessage(ChatColor.GREEN + "You are now an " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now an " + targetClass.getColor() + targetClass.getName());
		} else {
			player.sendMessage(ChatColor.GREEN + "You are now a " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now a " + targetClass.getColor() + targetClass.getName());
		}
	}
	
	@BaseCommand(aliases = { "modreset" }, description = "Reset a players data! DANGEROUS!", usage = "<Player>", min = 2, max = 2)
	public void onModResetCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_MODRESET, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[1]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		SNClass targetClass = new Human();
		
		player.setPlayerClass(targetClass, true);
		if(StringUtil.startsWithVowel(targetClass.getName())) {
			player.sendMessage(ChatColor.GREEN + "You are now an " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now an " + targetClass.getColor() + targetClass.getName());
		} else {
			player.sendMessage(ChatColor.GREEN + "You are now a " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now a " + targetClass.getColor() + targetClass.getName());
		}
	}

}
