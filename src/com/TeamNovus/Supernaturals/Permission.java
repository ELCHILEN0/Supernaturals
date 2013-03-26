package com.TeamNovus.Supernaturals;

import org.bukkit.command.CommandSender;

public enum Permission {
	COMMAND_ONLINE("commands.online"),
	COMMAND_INFO("commands.info"),
	COMMAND_INFO_OTHERS("commands.info.others"), 
	COMMAND_SPEED("commands.speed"),
	COMMAND_HEALTH("commands.health"),
	COMMAND_MANA("commands.mana"),
	COMMAND_HUNGER("commands.hunger"),
	COMMAND_LEVEL("commands.level"),
	COMMAND_INSPECT("commands.inspect"),
	COMMAND_EVOLVE("commands.evolve"),
	COMMAND_DEVOLVE("commands.devolve"),
	COMMAND_CLASSES("commands.classes"),
	COMMAND_RESET("commands.reset"),
	COMMAND_MODMANA("commands.modmana"),
	COMMAND_MODEXP("commands.modexp"),
	COMMAND_MODCLASS("commands.modclass"),
	COMMAND_MODRESET("commands.modreset");
	
	private String node;
	
	private Permission(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}
	
	private static String getPermission(Permission permission) {
		return "supernaturals." + permission.getNode();
	}
	
	public static Boolean has(Permission permission, CommandSender target) {
		return target.hasPermission(getPermission(permission));
	}
}
