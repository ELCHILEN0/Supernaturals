package com.TeamNovus.Supernaturals.Tasks;

import com.TeamNovus.Supernaturals.Database.StorageManager;

public class SaveTask implements Runnable {

	@Override
	public void run() {
		StorageManager.getInstance().savePlayers();
	}

}
