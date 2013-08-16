package com.TeamNovus.Bounties.Commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import com.TeamNovus.Bounties.Bounties;

public class Commands {
	private static List<Command> commands = new ArrayList<Command>();
	private static CommandExecutor commandExecutor = new CommandExecutor();
	private static Plugin plugin = Bounties.getPlugin();
	
	public static void register(Command command) {
		commands.add(command);
		
		if(command.getParent() == null) {
			PluginCommand cmd = getCommand(command.getAliases()[0], plugin);
						
			cmd.setAliases(Arrays.asList(command.getAliases()));
			cmd.setExecutor(commandExecutor);
						
			getCommandMap().register(plugin.getDescription().getName(), cmd);
		}
	}

	/*
	 * This method gets a command fromt he specified arguments passed to it.
	 * 
	 * All parts of the command are passed to method as labels.  A command typed as
	 * 		/command arg1 arg2 arg3
	 * will be made into an array as
	 * 		[command, arg1, arg2, arg3]
	 */
	public static Command getCommand(String... labels) {
		Command lastCommand = null;

		for (int i = 0; i < labels.length; i++) {
			String label = labels[i];
			Command nextCommand = null;
			
			if(i == 0) {
				// If we are on a base level then iterate through every command that does not have a parent.
				for(Command command : commands) {
					if(!(command.hasParentCommand())) {
						if(Arrays.asList(command.getAliases()).contains(label)) {
							nextCommand = command;
							
							continue;
						}
					}
				}
			} else {
				// Otherwise iterate through every command that is a child of lastCommand.
				for(Command command : lastCommand.getSubCommands()) {
					if(ArrayUtils.contains(command.getAliases(), label)) {
						nextCommand = command;
						
						continue;
					}
				}
			}	
			
			// If no command was found through the iteration then break out of the loop.
			if(nextCommand == null) {
				break;
			}
			
			lastCommand = nextCommand;
			
			// If the command has no children commands then break out of the loop
			if(!(nextCommand.hasSubCommand())) {
				break;
			}
		}

		return lastCommand;
	}
	
	public static String[] getArgs(String... labels) {
		Command lastCommand = null;

		for (int i = 0; i < labels.length; i++) {
			String label = labels[i];
			Command nextCommand = null;
			
			if(i == 0) {
				// If we are on a base level then iterate through every command that does not have a parent.
				for(Command command : commands) {
					if(!(command.hasParentCommand())) {
						if(Arrays.asList(command.getAliases()).contains(label)) {
							nextCommand = command;
							
							continue;
						}
					}
				}
			} else {
				// Otherwise iterate through every command that is a child of lastCommand.
				for(Command command : lastCommand.getSubCommands()) {
					if(ArrayUtils.contains(command.getAliases(), label)) {
						nextCommand = command;
						
						continue;
					}
				}
			}	
						
			// If no command was found through the iteration then break out of the loop.
			if(nextCommand == null) {
				String[] args = ArrayUtils.EMPTY_STRING_ARRAY;
				
				for (int j = i; j < labels.length; j++) {
					args = (String[]) ArrayUtils.add(args, args.length, labels[i]);
				}
				
				return args;
			}
			
			lastCommand = nextCommand;
			
			// If the command has no children commands then break out of the loop
			if(!(nextCommand.hasSubCommand())) {
				String[] args = ArrayUtils.EMPTY_STRING_ARRAY;
				
				for (int j = i + 1; j < labels.length; j++) {
					args = (String[]) ArrayUtils.add(args, args.length, labels[j]);
				}
				
				return args;
			}
			
			lastCommand = nextCommand;
		}

		return ArrayUtils.EMPTY_STRING_ARRAY;
	}
	
	private static CommandMap getCommandMap() {
		CommandMap commandMap = null;

		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);

				commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return commandMap;
	}

	private static PluginCommand getCommand(String name, Plugin plugin) {
		PluginCommand command = null;

		try {
			Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			c.setAccessible(true);

			command = c.newInstance(name, plugin);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return command;
	}
	
	public static List<Command> getCommands() {
		return commands;
	}

}
