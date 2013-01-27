package com.TeamNovus.Supernaturals;

import org.bukkit.entity.Player;

public enum Permission {
	CONVERT("convert");
	
	private String node;
	
	private Permission(String node) {
		this.node = node;
	}
	
	public String getNode() {
		return node;
	}
	
	public String getPermission(Permission permission) {
		return "supernaturals." + permission.getNode();
	}
	
	public Boolean has(Permission permission, Player player) {
		return player.hasPermission(getPermission(permission));
	}
}
