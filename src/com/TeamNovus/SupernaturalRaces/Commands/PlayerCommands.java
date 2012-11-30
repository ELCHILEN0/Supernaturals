package com.TeamNovus.SupernaturalRaces.Commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;
import com.TeamNovus.SupernaturalRaces.Util.Util;

public class PlayerCommands {

	@BaseCommand(aliases = "convert", description = "Convert yourself or another player to a race!", usage = "<Race> [Player]", min = 2, max = 3)
	public void onConvertCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 2) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
				return;
			}

			if(!(sender.hasPermission("supernaturalraces.convert."+args[1].toLowerCase()))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
				return;
			}

			if(SupernaturalRaces.getRaceManager().getBestRace(args[1]) == null) {
				sender.sendMessage(ChatColor.RED + "The specified race was not found!");
				return;
			}

			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer((Player) sender);
			Race race = SupernaturalRaces.getRaceManager().getBestRace(args[1]);

			if(player.getRace().equalsIgnoreCase(race.name())) {
				sender.sendMessage(ChatColor.RED + "You are already in this race!");
				return;
			}
			
			player.setRace(race.name());
			player.setPower(0);
			player.setBoundSpellId(0);
			sender.sendMessage(ChatColor.YELLOW + "You are now a(n) " + ChatColor.GREEN + race.name() + ChatColor.YELLOW + "!");
		} else {	
			if(!(sender.hasPermission("supernaturalraces.convert.others."+args[1].toLowerCase()))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
				return;
			}

			if(SupernaturalRaces.getRaceManager().getBestRace(args[1]) == null) {
				sender.sendMessage(ChatColor.RED + "The specified race was not found!");
				return;
			}

			if(SupernaturalRaces.getPlugin().getServer().getPlayer(args[2]) == null) {
				sender.sendMessage(ChatColor.RED + "The specified player is not online!");
				return;
			}

			SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(args[2]);
			Race race = SupernaturalRaces.getRaceManager().getBestRace(args[1]);

			if(player.getRace().equalsIgnoreCase(race.name())) {
				sender.sendMessage(ChatColor.RED + "The specified player is already in this race!");
				return;
			}

			player.setRace(race.name());
			player.setPower(0);
			SupernaturalRaces.getPlugin().getServer().getPlayer(args[2]).sendMessage(ChatColor.GREEN + "You are now a(n) " + ChatColor.YELLOW + race.name() + ChatColor.GREEN + "!");
			sender.sendMessage(ChatColor.YELLOW + Bukkit.getPlayer(args[2]).getDisplayName() + ChatColor.GREEN + " is now a(n) " + ChatColor.YELLOW + race.name() + ChatColor.GREEN + "!");
		}
	}
	
	@BaseCommand(aliases = "online", description = "View online players races!", usage = "", min = 1, max = 1)
	public void onOnlineCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender.hasPermission("supernaturalraces.online"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		for(Race race : SupernaturalRaces.getRaceManager().getRaces()) {
			List<String> players = new ArrayList<String>();
			for(Player p : SupernaturalRaces.getPlugin().getServer().getOnlinePlayers()) {
				SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(p);
				if(race.name().equalsIgnoreCase(player.getRace())) {
					players.add(race.color() + p.getName());
				}
				
			}
			
			if(players.size() > 0) {
				sender.sendMessage(race.color() + race.name() + ChatColor.RESET + ": " + StringUtils.join(players, ", "));
			}
		}
	}

	@BaseCommand(aliases = "races", description = "View all the availiable races!", usage = "")
	public void onRacesCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender.hasPermission("supernaturalraces.races"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		sender.sendMessage(ChatColor.BLUE + "Availiable Races:");
		for(Race race : SupernaturalRaces.getRaceManager().getRaces()) {
			sender.sendMessage("  " + race.color() + race.name());
		}
	}

	@BaseCommand(aliases = "race", description = "View information about a race!", usage = "<Race>", min = 2, max = 2)
	public void onRaceInfoCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(SupernaturalRaces.getRaceManager().getBestRace(args[1]) == null) {
			sender.sendMessage(ChatColor.RED + "The specified race was not found!");
			return;
		}

		Race race = SupernaturalRaces.getRaceManager().getBestRace(args[1]);

		sender.sendMessage(ChatColor.GOLD + race.name() + ":");
		sender.sendMessage(ChatColor.BLUE + "   Max Power: " + ChatColor.YELLOW + race.maxPower());
		sender.sendMessage(ChatColor.BLUE + "   Spells:");
		for(Spell spell : race.spells()) {
			sender.sendMessage(ChatColor.GOLD + "      - " + spell.name());
		}
	}
	
	@BaseCommand(aliases = "spell", description = "View information about a spell!", usage = "<Spell>", min = 2, max = 2)
	public void onSpellInfoCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Spell spell = SupernaturalRaces.getRaceManager().getBestSpell(args[1].toLowerCase());

		if(spell == null) {
			sender.sendMessage(ChatColor.RED + "The specified spell was not found!");
			return;
		}
		
		sender.sendMessage(ChatColor.GOLD + "" + spell.name());
		sender.sendMessage(ChatColor.BLUE + "   Requires:");
		if(spell.required().getMoneyCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Money: " + ChatColor.YELLOW + spell.required().getMoneyCost());
		if(spell.required().getExpCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Experience: " + ChatColor.YELLOW + spell.required().getExpCost());
		if(spell.required().getHealthCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Health: " + ChatColor.YELLOW + spell.required().getHealthCost());
		if(spell.required().getHungerCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Hunger: " + ChatColor.YELLOW + spell.required().getHungerCost());
		if(spell.required().getPowerCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Power: " + ChatColor.YELLOW + spell.required().getPowerCost());
		if(spell.required().getItemBagCost() != new ItemBag());
			sender.sendMessage(ChatColor.BLUE + "      Items: " + ChatColor.YELLOW + spell.required().getItemBagCost().toString());
		sender.sendMessage(ChatColor.BLUE + "   Consumes: " + ChatColor.YELLOW);
		if(spell.consume().getMoneyCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Money: " + ChatColor.YELLOW + spell.consume().getMoneyCost());
		if(spell.consume().getExpCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Experience: " + ChatColor.YELLOW + spell.consume().getExpCost());
		if(spell.consume().getHealthCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Health: " + ChatColor.YELLOW + spell.consume().getHealthCost());
		if(spell.consume().getHungerCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Hunger: " + ChatColor.YELLOW + spell.consume().getHungerCost());
		if(spell.consume().getPowerCost() != 0)
			sender.sendMessage(ChatColor.BLUE + "      Power: " + ChatColor.YELLOW + spell.consume().getPowerCost());
		if(!spell.consume().getItemBagCost().equals(new ItemBag()));
			sender.sendMessage(ChatColor.BLUE + "      Items: " + ChatColor.YELLOW + spell.consume().getItemBagCost().toString());
		
	}

	@BaseCommand(aliases = "power", description = "View your total power!", usage = "")
	public void onPowerCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be run from the console!");
			return;
		}

		if(!(sender.hasPermission("supernaturalraces.power"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer((Player) sender);
		Race race = SupernaturalRaces.getRaceManager().getRace(player);
		sender.sendMessage(ChatColor.YELLOW + "Your Power: " + ChatColor.GREEN + player.getPower() + "/" + race.maxPower());

	}
	
	@BaseCommand(aliases = "powerboost", description = "Boost a players power!", usage = "<Player> <Ammount>", min = 3, max = 3)
	public void onPowerBoostCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender.hasPermission("supernaturalraces.powerboost"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
		
		if(SupernaturalRaces.getPlugin().getServer().getPlayer(args[1]) == null) {
			sender.sendMessage(ChatColor.RED + "The specified player is not online!");
			return;
		}

		SNPlayer player = SupernaturalRaces.getPlayerManager().getPlayer(args[1]);

		if(!(Util.isInt(args[2]))) {
			sender.sendMessage(ChatColor.RED + "Power must be an Integer");
			return;
		}
		
		player.setPower(player.getPower() + Integer.valueOf(args[2]));
		sender.sendMessage(ChatColor.YELLOW + "Added Power: " + ChatColor.GREEN + Integer.valueOf(args[2]));
	}
}
