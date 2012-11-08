package com.TeamNovus.SupernaturalRaces.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNPlayer;
import com.TeamNovus.SupernaturalRaces.Models.Spell;
import com.TeamNovus.SupernaturalRaces.Util.ItemBag;

public class PlayerCommands {

	@BaseCommand(aliases = "convert", description = "Convert yourself or another player to a race!", usage = "convert <Race> [Player]", min = 2, max = 3)
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
			SupernaturalRaces.getPlugin().getServer().getPlayer(args[2]).sendMessage(ChatColor.GREEN + "You are now a(n) " + ChatColor.YELLOW + race.name() + ChatColor.GREEN + "!");
			sender.sendMessage(ChatColor.YELLOW + Bukkit.getPlayer(args[2]).getDisplayName() + ChatColor.GREEN + " is now a(n) " + ChatColor.YELLOW + race.name() + ChatColor.GREEN + "!");
		}

	}

	@BaseCommand(aliases = "races", description = "View all the availiable races!", usage = "races")
	public void onRacesCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender.hasPermission("supernaturalraces.races"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		sender.sendMessage(ChatColor.BLUE + "Availiable Races:");
		for(Race race : SupernaturalRaces.getRaceManager().getRaces()) {
			sender.sendMessage("   " + ChatColor.GOLD + race.name());
		}
	}

	@BaseCommand(aliases = "race", description = "View information about a race!", usage = "race <Race>", min = 2, max = 2)
	public void onRaceInfoCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(SupernaturalRaces.getRaceManager().getBestRace(args[1]) == null) {
			sender.sendMessage(ChatColor.RED + "The specified race was not found!");
			return;
		}

		Race race = SupernaturalRaces.getRaceManager().getBestRace(args[1]);

		sender.sendMessage(ChatColor.GOLD + race.name() + ":");
		sender.sendMessage(ChatColor.BLUE + "   Max Power: " + ChatColor.YELLOW + race.maxPower());
		sender.sendMessage(ChatColor.BLUE + "   Default Power Gain: " + ChatColor.YELLOW + race.powerIncrement());
		sender.sendMessage(ChatColor.BLUE + "Info: " + ChatColor.YELLOW + race.info());
		sender.sendMessage(ChatColor.BLUE + "   Spells:");
		for(Spell spell : race.spells()) {
			sender.sendMessage(ChatColor.GOLD + "      - " + spell.name());
		}
	}
	
	@BaseCommand(aliases = "spell", description = "View information about a spell!", usage = "spell <Spell>", min = 2, max = 2)
	public void onSpellInfoCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(SupernaturalRaces.getRaceManager().getBestSpell(args[1]) == null) {
			sender.sendMessage(ChatColor.RED + "The specified spell was not found!");
			return;
		}
		
		Spell spell = SupernaturalRaces.getRaceManager().getBestSpell(args[1]);
		sender.sendMessage(ChatColor.GOLD + "" + spell.name());
		sender.sendMessage(ChatColor.BLUE + "   Info: " + ChatColor.YELLOW + spell.info());
		sender.sendMessage(ChatColor.BLUE + "   Bound to: " + ChatColor.YELLOW + spell.binding().name());
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


	@BaseCommand(aliases = "power", description = "View your total power!", usage = "power")
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
}
