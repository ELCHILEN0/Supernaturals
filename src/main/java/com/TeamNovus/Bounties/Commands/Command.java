package com.TeamNovus.Bounties.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

public abstract class Command {
	private Command 	parent;
	private String[] 	aliases;
	private String		description;
	private String 		usage;
	private String		usageMessage;
	private String 		permission;
	private String		permissionMessage;
	private boolean		allowPlayer = true;
	private boolean 	allowConsole = true;
	private String		playerDisallowedMessage;
	private String		consoleDisallowedMessage;
	
	public Command(Command parent, String... aliases) throws InstantiationException {
		this.parent = parent;
		
		if (aliases.length == 0) {
			throw new InstantiationException("The command must have 1 or more alias.");
		}
		
		for (String alias : aliases) {
			if (alias.contains(" ")) {
				throw new InstantiationException("Invalid characters.  An alias cannot contain spaces.");
			}
		}
		
		this.aliases = aliases;
	}
	
	public Command(String... aliases) throws InstantiationException {
		this(null, aliases);
	}
	
	public Command getParent() {
		return parent;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public String getDescription() {
		return description;
	}
	
	protected Command setDescription(String description) {
		this.description = description;
		
		return this;
	}
	
	public String getUsage() {
		return usage;
	}
	
	protected Command setUsage(String usage) {
		this.usage = usage;
		
		return this;
	}
	
	public int getMinArgs() {
		String[] args = usage.split(" ");
		
		int count = 0;
		
		for (String arg : args) {
			if(arg.startsWith("<") && arg.endsWith(">")) 
				count++;
		}
		
		return count;
	}
	
	public int getMaxArgs() {
		String[] args = usage.split(" ");
		
		int count = 0;
		
		for (String arg : args) {
			if((arg.startsWith("<") && arg.endsWith(">")) || (arg.startsWith("[") && arg.endsWith("]"))) 
				count++;
		}
		
		return count;
	}
	
	public String getUsageMessage() {
		return usageMessage;
	}
	
	protected Command setUsageMessage(String usageMessage) {
		this.usageMessage = usageMessage;
		
		return this;
	}
	
	public String getPermission() {
		return permission;
	}
	
	protected Command setPermission(String permission) {
		this.permission = permission;
		
		return this;
	}
	
	public String getPermissionMessage() {
		return permissionMessage;
	}

	protected Command setPermissionMessage(String permissionMessage) {
		this.permissionMessage = permissionMessage;
		
		return this;
	}
	
	public boolean doesAllowPlayer() {
		return allowPlayer;
	}
	
	public boolean doesntAllowPlayer() {
		return !(allowPlayer);
	}
	
	protected Command setAllowPlayer(boolean allowPlayer) {
		this.allowPlayer = allowPlayer;
		
		return this;
	}
	
	public String getPlayerDisallowedMessage() {
		return playerDisallowedMessage;
	}
	
	protected Command setPlayerDisallowedMessage(String playerDisallowedMessage) {
		this.playerDisallowedMessage = playerDisallowedMessage;
		
		return this;
	}
	
	public boolean doesAllowConsole() {
		return allowConsole;
	}
	
	public boolean doesntAllowConsole() {
		return !(allowConsole);
	}	
	
	protected Command setAllowConsole(boolean allowConsole) {
		this.allowConsole = allowConsole;
		
		return this;
	}
	
	public String getConsoleDisallowedMessage() {
		return consoleDisallowedMessage;
	}
	
	protected Command setConsoleDisallowedMessage(String consoleDisallowedMessage) {
		this.consoleDisallowedMessage = consoleDisallowedMessage;
		
		return this;
	}

	public List<Command> getSubCommands() {
		ArrayList<Command> commands = new ArrayList<Command>();
		
		for(Command command : Commands.getCommands()) {
			if(command.getParent() == this) {
				commands.add(command);
			}
		}
		
		return commands;
	}
	
	public boolean hasSubCommand() {
		for(Command command : Commands.getCommands()) {
			if(command.getParent() == this) {
				return true;
			}
		}

		return false;
	}
	
	public boolean isSubCommand(Command command) {
		return command.getParent() == this;
	}

	public boolean hasParentCommand() {
		return this.getParent() != null;
	}
	
	public boolean isParentCommand(Command command) {
		return this.getParent() == command;
	}
	
	public void register() {
		Commands.register(this);
	}
	
	public abstract void onCommand(String[] labels, CommandSender sender, String[] args);
	public abstract List<String> onTabComplete();
}
