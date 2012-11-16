package com.TeamNovus.SupernaturalRaces.Tasks;

import com.TeamNovus.SupernaturalRaces.Listeners.DefaultServerListener;

public class ServerTickTask implements Runnable {
	
	@Override
	public void run() {
		new DefaultServerListener().onServerTick();		
	}
	
}
