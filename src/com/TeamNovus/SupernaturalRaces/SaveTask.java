package com.TeamNovus.SupernaturalRaces;


public class SaveTask implements Runnable {
	private SupernaturalRaces plugin;
	
	public SaveTask(SupernaturalRaces plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		plugin.getPlayerManager().save();
	}
}
