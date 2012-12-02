package com.TeamNovus.SupernaturalRaces.Tasks;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;

public class SaveTask implements Runnable {
	
	@Override
	public void run() {
		SupernaturalRaces.getDatabaseManager().savePlayers();
	}
}
