package com.TeamNovus.Supernaturals;

import org.bukkit.command.CommandSender;

public enum Permission {
	COMMAND_ONLINE("online"), COMMAND_INFO("info"), COMMAND_INFO_OTHERS("info.others"), COMMAND_CONVERT("convert");
	
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
