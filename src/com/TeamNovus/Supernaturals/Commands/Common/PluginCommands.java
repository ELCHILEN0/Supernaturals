package com.TeamNovus.Supernaturals.Commands.Common;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Permission;
import com.TeamNovus.Supernaturals.SNClasses;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Classes.Human;
import com.TeamNovus.Supernaturals.Commands.BaseCommand;
import com.TeamNovus.Supernaturals.Commands.CommandManager;
import com.TeamNovus.Supernaturals.Player.SNClass;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Ability;
import com.TeamNovus.Supernaturals.Player.Class.Power;
import com.TeamNovus.Supernaturals.Util.StringUtil;

public class PluginCommands {
	/* Player Commands:
	 * 	Default:
	 *  /sn online
	 *  /sn info [Player]
	 *  /sn mana
	 *  /sn speed
	 *  /sn health
	 *  /sn hunger
	 *  /sn level
	 *  /sn classes
	 *  /sn specs <Class>
	 *  /sn evolve <Class>
	 *  /sn devolve
	 *  /sn reset
	 *  
	 */
	
	public String fillBar(int bars, ChatColor fullColor, ChatColor emptyColor, int amount, int max) {
		int fill = (int) (amount * 1.0 * bars/max);
		String bar = new String();
		
		for (int i = 0; i < fill; i++) {
			bar += fullColor + "|";
		}
		
		for (int i = 0; i < 50 - fill; i++) {
			bar += emptyColor + "|";
		}
		
		return bar;
	}

	@BaseCommand(aliases = { "online" }, description = "View information on online players.", usage = "")
	public void onOnlineCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_ONLINE, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Online Players" + CommandManager.getDarkColor() + " ]______________");
		
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
				if(player.isOnline()) {
					names.add(player.getName());
				}
			}

			lines.add(playerClass.getColor() + playerClass.getName() + ": " + StringUtils.join(names, ", "));

		}

		for(String line : lines) {
			sender.sendMessage(line);
		}
	}

	@BaseCommand(aliases = { "info", "stats" }, description = "View information on online players.", usage = "[Player]", min = 1, max = 2)
	public void onInfoCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 1 && Permission.has(Permission.COMMAND_INFO, sender)) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
				return;
			}

			SNPlayer player = SNPlayers.i.get((Player) sender);

			sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Info" + CommandManager.getDarkColor() + " ]______________");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Name: " + ChatColor.RESET + player.getName());
			sender.sendMessage(CommandManager.getDarkColor() + "Class: " + ChatColor.RESET + player.getPlayerClass().getColor() + player.getPlayerClass().getName());
			sender.sendMessage(CommandManager.getDarkColor() + "Level: " + ChatColor.RESET + player.getLevel() + "/" + player.getPlayerClass().getMaxLevel());
			
			sender.sendMessage(CommandManager.getDarkColor() + "Experience: "
								+ ChatColor.RED + "[" + fillBar(50, ChatColor.GOLD, ChatColor.GRAY, player.getExperience(), player.getExperienceFor(player.getLevel() + 1)) + ChatColor.RED + "]"
								+ " (" + player.getExperience() + "/" + player.getExperienceFor(player.getLevel() + 1) + ")");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Health: "
								+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_RED, ChatColor.GRAY, player.getHealth(), player.getMaxHealth()) + ChatColor.RED + "]"
								+ " (" + player.getHealth() + "/" + player.getMaxHealth() + ")");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Mana: " 
					 			+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_BLUE, ChatColor.GRAY, player.getMana(), player.getMaxMana()) + ChatColor.RED + "]"
								+ " (" + player.getMana() + "/" + player.getMaxMana() + ")");
						
			sender.sendMessage(CommandManager.getDarkColor() + "Hunger: " 
								+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_GREEN, ChatColor.GRAY, player.getFoodLevel(), player.getMaxFoodLevel()) + ChatColor.RED + "]"
								+ " (" + player.getFoodLevel() + "/" + player.getMaxFoodLevel() + ")");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Speed: " + ChatColor.RESET + player.getSpeed() + "/0.2");

		} else if(args.length == 2 && Permission.has(Permission.COMMAND_INFO_OTHERS, sender)) {
			SNPlayer player = SNPlayers.i.getPlayer(args[1]);

			if(player == null) {
				sender.sendMessage(ChatColor.RED + "The specified player could not be found!");
				return;
			}

			sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Info" + CommandManager.getDarkColor() + " ]______________");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Name: " + ChatColor.RESET + player.getName());
			sender.sendMessage(CommandManager.getDarkColor() + "Class: " + ChatColor.RESET + player.getPlayerClass().getColor() + player.getPlayerClass().getName());
			sender.sendMessage(CommandManager.getDarkColor() + "Level: " + ChatColor.RESET + player.getLevel() + "/" + player.getPlayerClass().getMaxLevel());
			
			sender.sendMessage(CommandManager.getDarkColor() + "Experience: "
								+ ChatColor.RED + "[" + fillBar(50, ChatColor.GOLD, ChatColor.GRAY, player.getExperience(), player.getExperienceFor(player.getLevel() + 1)) + ChatColor.RED + "]"
								+ " (" + player.getExperience() + "/" + player.getExperienceFor(player.getLevel() + 1) + ")");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Health: "
								+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_RED, ChatColor.GRAY, player.getHealth(), player.getMaxHealth()) + ChatColor.RED + "]"
								+ " (" + player.getHealth() + "/" + player.getMaxHealth() + ")");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Mana: " 
					 			+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_BLUE, ChatColor.GRAY, player.getMana(), player.getMaxMana()) + ChatColor.RED + "]"
								+ " (" + player.getMana() + "/" + player.getMaxMana() + ")");
						
			sender.sendMessage(CommandManager.getDarkColor() + "Hunger: " 
								+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_GREEN, ChatColor.GRAY, player.getFoodLevel(), player.getMaxFoodLevel()) + ChatColor.RED + "]"
								+ " (" + player.getFoodLevel() + "/" + player.getMaxFoodLevel() + ")");
			
			sender.sendMessage(CommandManager.getDarkColor() + "Speed: " + ChatColor.RESET + player.getSpeed() + "/0.2");
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
		}
	}

	@BaseCommand(aliases = { "speed" }, description = "View your current speed.", usage = "")
	public void onSpeedCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_SPEED, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Speed" + CommandManager.getDarkColor() + " ]______________");
		sender.sendMessage(CommandManager.getDarkColor() + "Speed: " + ChatColor.RESET + player.getSpeed() + "/0.2");
	}

	@BaseCommand(aliases = { "mana" }, description = "View your current mana.", usage = "")
	public void onManaCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_MANA, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Mana" + CommandManager.getDarkColor() + " ]______________");
		
		sender.sendMessage(CommandManager.getDarkColor() + "Mana: " 
	 			+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_BLUE, ChatColor.GRAY, player.getMana(), player.getMaxMana()) + ChatColor.RED + "]"
				+ " (" + player.getMana() + "/" + player.getMaxMana() + ")");
	}

	@BaseCommand(aliases = { "health" }, description = "View your current health.", usage = "")
	public void onHealthCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_HEALTH, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Health" + CommandManager.getDarkColor() + " ]______________");
		
		sender.sendMessage(CommandManager.getDarkColor() + "Health: "
				+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_RED, ChatColor.GRAY, player.getHealth(), player.getMaxHealth()) + ChatColor.RED + "]"
				+ " (" + player.getHealth() + "/" + player.getMaxHealth() + ")");
	}

	@BaseCommand(aliases = { "hunger", "food" }, description = "View your current health.", usage = "")
	public void onHungerCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_HUNGER, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Hunger" + CommandManager.getDarkColor() + " ]______________");
		
		sender.sendMessage(CommandManager.getDarkColor() + "Hunger: " 
				+ ChatColor.RED + "[" + fillBar(50, ChatColor.DARK_GREEN, ChatColor.GRAY, player.getFoodLevel(), player.getMaxFoodLevel()) + ChatColor.RED + "]"
				+ " (" + player.getFoodLevel() + "/" + player.getMaxFoodLevel() + ")");
	}

	@BaseCommand(aliases = { "level" }, description = "View your current level.", usage = "")
	public void onLevelCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_LEVEL, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Level" + CommandManager.getDarkColor() + " ]______________");
		
		sender.sendMessage(CommandManager.getDarkColor() + "Level: " + ChatColor.RESET + player.getLevel() + "/" + player.getPlayerClass().getMaxLevel());
		sender.sendMessage(CommandManager.getDarkColor() + "Experience: "
							+ ChatColor.RED + "[" + fillBar(50, ChatColor.GOLD, ChatColor.GRAY, player.getExperience(), player.getExperienceFor(player.getLevel() + 1)) + ChatColor.RED + "]"
							+ " (" + player.getExperience() + "/" + player.getExperienceFor(player.getLevel() + 1) + ")");
	}

	@BaseCommand(aliases = { "powers" }, description = "View powers for a class!", usage = "[Class]", min = 1, max = 2)
	public void onPowersCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_POWERS, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player) && args.length == 1) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Class Powers" + CommandManager.getDarkColor() + " ]______________");
		
		SNClass targetClass = player.getPlayerClass();
		
		if(args.length == 2) {
			targetClass = SNClasses.i.getBestClass(args[1]);
		}
		
		if(targetClass == null) {
			sender.sendMessage(ChatColor.RED + "The specified class was not found!");
			return;
		}
		
		boolean containsContent = false;
		
		for (int i = 1; i <= targetClass.getMaxLevel(); i++) {
			if(!(targetClass.hasChangedFrom(i - 1, i))) {
				continue;
			}
			
			if(targetClass.getUniquePowers(i).size() > 0) {
				containsContent = true;
				
				for(Power power : targetClass.getUniquePowers(i)) {
					if(player.getPowers().contains(power)) {
						sender.sendMessage(ChatColor.GREEN + "Level " + i + " - " + ChatColor.GOLD + power.getName() + ": " + power.getDesc());
					} else {
						sender.sendMessage(ChatColor.RED + "Level " + i + " - "  + ChatColor.GOLD + power.getName() + ": " + power.getDesc());
					}
				}
			}
		}
		
		if(!(containsContent)) {
			sender.sendMessage(ChatColor.RED + "This class does not have any powers!");
		}
	}
	
	@BaseCommand(aliases = { "abilities" }, description = "View abilities for a class!", usage = "[Class]", min = 1, max = 2)
	public void onAbilitiesCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_ABILITIES, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player) && args.length == 1) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Class Abilities" + CommandManager.getDarkColor() + " ]______________");
		
		SNClass targetClass = player.getPlayerClass();
		
		if(args.length == 2) {
			targetClass = SNClasses.i.getBestClass(args[1]);
		}
		
		if(targetClass == null) {
			sender.sendMessage(ChatColor.RED + "The specified class was not found!");
			return;
		}
		
		boolean containsContent = false;
		
		for (int i = 1; i <= targetClass.getMaxLevel(); i++) {
			if(!(targetClass.hasChangedFrom(i - 1, i))) {
				continue;
			}
			
			if(targetClass.getUniqueAbilities(i).size() > 0) {
				containsContent = true;
				
				for(Ability ability : targetClass.getUniqueAbilities(i)) {
					if(player.getAbilities().contains(ability)) {
						sender.sendMessage(ChatColor.GREEN + "Level " + i + " - " + ChatColor.GOLD + ability.getName() + ": " + ability.getDesc());
					} else {
						sender.sendMessage(ChatColor.RED + "Level " + i + " - "  + ChatColor.GOLD + ability.getName() + ": " + ability.getDesc());
					}
				}
			}
		}
		
		if(!(containsContent)) {
			sender.sendMessage(ChatColor.RED + "This class does not have any abilities!");
		}
	}
	
	@BaseCommand(aliases = { "specs" }, description = "View specifications for a class!", usage = "[Class]", min = 1, max = 2)
	public void onSpecsCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_SPECS, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player) && args.length == 1) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}
		
		SNPlayer player = SNPlayers.i.get((Player) sender);

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Class Specs" + CommandManager.getDarkColor() + " ]______________");
		
		SNClass targetClass = player.getPlayerClass();
		
		if(args.length == 2) {
			targetClass = SNClasses.i.getBestClass(args[1]);
		}
		
		if(targetClass == null) {
			sender.sendMessage(ChatColor.RED + "The specified class was not found!");
			return;
		}
		
		boolean containsContent = false;
		
		for (int i = 1; i <= targetClass.getMaxLevel(); i++) {
			if(!(targetClass.hasChangedFrom(i - 1, i))) {
				continue;
			}
			
			containsContent = true;

			if(player.getLevel() >= i && player.getPlayerClass().equals(targetClass)) {
				if(targetClass.getMaxHealth(i) != targetClass.getMaxHealth(i - 1)) {
					sender.sendMessage(ChatColor.GREEN + "Level " + i + " - " + ChatColor.GOLD + "Health: " + targetClass.getMaxHealth(i));
				}
				
				if(targetClass.getMaxMana(i) != targetClass.getMaxMana(i - 1)) {
					sender.sendMessage(ChatColor.GREEN + "Level " + i + " - " + ChatColor.GOLD + "Mana: " + targetClass.getMaxMana(i));
				}
				
				if(targetClass.getMaxFoodLevel(i) != targetClass.getMaxFoodLevel(i - 1)) {
					sender.sendMessage(ChatColor.GREEN + "Level " + i + " - " + ChatColor.GOLD + "Hunger: " + targetClass.getMaxFoodLevel(i));
				}
				
				if(targetClass.getSpeed(i) != targetClass.getSpeed(i - 1)) {
					sender.sendMessage(ChatColor.GREEN + "Level " + i + " - " + ChatColor.GOLD + "Speed: " + targetClass.getSpeed(i));
				}


			} else {
				if(targetClass.getMaxHealth(i) != targetClass.getMaxHealth(i - 1)) {
					sender.sendMessage(ChatColor.RED + "Level " + i + " - " + ChatColor.GOLD + "Health: " + targetClass.getMaxHealth(i));
				}
				
				if(targetClass.getMaxMana(i) != targetClass.getMaxMana(i - 1)) {
					sender.sendMessage(ChatColor.RED + "Level " + i + " - " + ChatColor.GOLD + "Mana: " + targetClass.getMaxMana(i));
				}
				
				if(targetClass.getMaxFoodLevel(i) != targetClass.getMaxFoodLevel(i - 1)) {
					sender.sendMessage(ChatColor.RED + "Level " + i + " - " + ChatColor.GOLD + "Hunger: " + targetClass.getMaxFoodLevel(i));
				}
				
				if(targetClass.getSpeed(i) != targetClass.getSpeed(i - 1)) {
					sender.sendMessage(ChatColor.RED + "Level " + i + " - " + ChatColor.GOLD + "Speed: " + targetClass.getSpeed(i));
				}
			}
		}
		
		if(!(containsContent)) {
			sender.sendMessage(ChatColor.RED + "This class does not have any specs!");
		}
	}
	
	@BaseCommand(aliases = { "classes" }, description = "View available classes.", usage = "")
	public void onClassesCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_CLASSES, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
		}

		sender.sendMessage(CommandManager.getDarkColor() + "______________[ " + CommandManager.getLightColor() + "Player Classes" + CommandManager.getDarkColor() + " ]______________");
		
		SNPlayer player = SNPlayers.i.get((Player) sender);

		if(player.getPlayerClass().getParentClass() != null)
			sender.sendMessage(CommandManager.getDarkColor() + "Parent Class: " + ChatColor.RESET + player.getPlayerClass().getParentClass().getColor() + player.getPlayerClass().getParentClass().getName());

		ArrayList<String> joinableClasses = new ArrayList<String>();

		for(SNClass c : player.getJoinableClasses()) {
			joinableClasses.add(c.getColor() + c.getName());
		}

		if(joinableClasses.size() > 0) {
			if(joinableClasses.size() == 1) {
				sender.sendMessage(CommandManager.getDarkColor() + "Unlocked Class: " + ChatColor.RESET + StringUtils.join(joinableClasses, ", "));
			} else {
				sender.sendMessage(CommandManager.getDarkColor() + "Unlocked Classes: " + ChatColor.RESET + StringUtils.join(joinableClasses, ", "));
			}
		}


		ArrayList<String> unjoinableClasses = new ArrayList<String>();

		for(SNClass c : player.getPlayerClass().getAllJoinableClasses()) {
			if(!(player.getJoinableClasses().contains(c)))
				unjoinableClasses.add(c.getColor() + c.getName());
		}

		if(unjoinableClasses.size() > 0) {
			if(unjoinableClasses.size() == 1) {
				sender.sendMessage(CommandManager.getDarkColor() + "Locked Class: " + ChatColor.RESET + StringUtils.join(unjoinableClasses, ", "));
			} else {
				sender.sendMessage(CommandManager.getDarkColor() + "Locked Classes: " + ChatColor.RESET + StringUtils.join(unjoinableClasses, ", "));
			}
		}
	}

	@BaseCommand(aliases = { "evolve", "convert" }, description = "Evolve to another class.", usage = "<Class>", min = 2, max = 2)
	public void onEvoloveCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be ran as a player!");
			return;
		}

		if(!(Permission.has(Permission.COMMAND_DEVOLVE, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);
		SNClass targetClass = SNClasses.i.getBestClass(args[1]);

		if(targetClass == null) {
			sender.sendMessage(ChatColor.RED + "The specified class was not found!");
			return;
		}

		if(player.getPlayerClass().equals(targetClass)) {
			sender.sendMessage(ChatColor.RED + "You are already in this class!");
			return;
		}

		if(!(player.getJoinableClasses().contains(targetClass))) {
			if(player.getPlayerClass().getAllJoinableClasses().contains(targetClass)) {
				sender.sendMessage(ChatColor.RED + "You are not a high enough level to evolve into this class!");
			} else {
				sender.sendMessage(ChatColor.RED + "You cannot evolve into this class!");
			}
			return;
		}

		player.setPlayerClass(targetClass, true);
		player.setExperience(0);

		if(StringUtil.startsWithVowel(targetClass.getName())) {
			player.sendMessage(ChatColor.GREEN + "You are now an " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now an " + targetClass.getColor() + targetClass.getName());
		} else {
			player.sendMessage(ChatColor.GREEN + "You are now a " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now a " + targetClass.getColor() + targetClass.getName());
		}
	}

	@BaseCommand(aliases = { "devolve" }, description = "Devolve to your parent race.", usage = "")
	public void onDevolveCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_DEVOLVE, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		SNClass targetClass = player.getPlayerClass().getParentClass();

		if(targetClass == null) {
			sender.sendMessage(ChatColor.RED + "You cannot devolve any further!");
			return;
		}

		player.setPlayerClass(targetClass, true);
		player.setExperience(0);

		if(StringUtil.startsWithVowel(targetClass.getName())) {
			player.sendMessage(ChatColor.GREEN + "You are now an " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now an " + targetClass.getColor() + targetClass.getName());
		} else {
			player.sendMessage(ChatColor.GREEN + "You are now a " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now a " + targetClass.getColor() + targetClass.getName());
		}
	}

	@BaseCommand(aliases = { "reset" }, description = "Reset all your data. DANGEROUS!", usage = "")
	public void onResetCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(Permission.has(Permission.COMMAND_RESET, sender))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be ran from the console!");
			return;
		}

		SNPlayer player = SNPlayers.i.get((Player) sender);

		SNClass targetClass = new Human();

		player.setPlayerClass(targetClass, true);
		player.setExperience(0);

		if(StringUtil.startsWithVowel(targetClass.getName())) {
			player.sendMessage(ChatColor.GREEN + "You are now an " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now an " + targetClass.getColor() + targetClass.getName());
		} else {
			player.sendMessage(ChatColor.GREEN + "You are now a " + targetClass.getColor() + targetClass.getName());
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " is now a " + targetClass.getColor() + targetClass.getName());
		}
	}

}
