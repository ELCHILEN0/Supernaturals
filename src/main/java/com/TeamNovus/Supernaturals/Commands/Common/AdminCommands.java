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
	
	@BaseCommand(aliases = { "modmana" }, desc = "Change a players mana.", permission = Permission.COMMAND_MODMANA, usage = "<Give/Take/Reset> <Player> <Mana>", min = 3, max = 3)
	public void onModManaCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(args[0].equalsIgnoreCase("give")) && !(args[0].equalsIgnoreCase("take")) && !(args[0].equalsIgnoreCase("reset"))) {
			sender.sendMessage(ChatColor.RED + "The specified action does not exist!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[1]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		if(!(StringUtil.isInteger(args[2]))) {
			sender.sendMessage(ChatColor.RED + "The specified value is not an Integer!");
			return;
		}
		
		int mana = Integer.valueOf(args[2]);
		
		if(args[0].equalsIgnoreCase("give")) {
			player.setMana(player.getMana() + mana, false);
			
			player.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana has been given to your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana has been given to " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[0].equalsIgnoreCase("take")) {
			player.setMana(player.getMana() - mana, false);
			
			player.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.RED + " mana has been removed from your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana has been taken from " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[0].equalsIgnoreCase("reset")) {
			player.setMana(mana, false);
			
			player.sendMessage(ChatColor.GREEN + "Your mana has been set to " + ChatColor.YELLOW + "" + mana + ChatColor.GREEN + " mana.");
			sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "'s mana has been reset to " + ChatColor.YELLOW + mana + ChatColor.GREEN +" mana!"); 
		}
	}
	
	@BaseCommand(aliases = { "modexp" }, desc = "Change a players exp.", permission = Permission.COMMAND_MODEXP, usage = "<Give/Take/Reset> <Player> <Exp>", min = 3, max = 3)
	public void onModExpCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(args[0].equalsIgnoreCase("give")) && !(args[0].equalsIgnoreCase("take")) && !(args[0].equalsIgnoreCase("reset"))) {
			sender.sendMessage(ChatColor.RED + "The specified action does not exist!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[1]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		if(!(StringUtil.isInteger(args[2]))) {
			sender.sendMessage(ChatColor.RED + "The specified value is not an Integer!");
			return;
		}
		
		int exp = Integer.valueOf(args[2]);
		
		if(args[0].equalsIgnoreCase("give")) {
			player.setExperience(player.getExperience() + exp);
			
			player.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp has been given to your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp has been given to " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[0].equalsIgnoreCase("take")) {
			player.setExperience(player.getExperience() - exp);
			
			player.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.RED + " exp has been removed from your player.");
			sender.sendMessage(ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp has been taken from " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " sucessfully!"); 
		} else if(args[0].equalsIgnoreCase("reset")) {
			player.setExperience(exp);
			
			player.sendMessage(ChatColor.GREEN + "Your exp has been set to " + ChatColor.YELLOW + "" + exp + ChatColor.GREEN + " exp.");
			sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "'s exp has been reset to " + ChatColor.YELLOW + exp + ChatColor.GREEN +" exp!"); 
		}
	}
	
	@BaseCommand(aliases = { "modclass" }, desc = "Change a players class.", permission = Permission.COMMAND_MODCLASS, usage = "<Player> <Class>", min = 2, max = 2)
	public void onModClassCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		SNPlayer player = SNPlayers.i.getPlayer(args[0]);
		
		if(player == null) {
			sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
			return;
		}

		SNClass targetClass = SNClasses.i.getBestClass(args[1]);
		
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
	
	@BaseCommand(aliases = { "modreset" }, desc = "Reset a players data.", permission = Permission.COMMAND_MODRESET, usage = "<Player>", min = 1, max = 1)
	public void onModResetCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_MODRESET, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.getPlayer(args[0]);
		
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
