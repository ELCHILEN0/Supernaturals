package com.TeamNovus.Bounties.Tasks;

import com.TeamNovus.Bounties.Database.StorageManager;

public class SaveTask implements Runnable {

	public void run() {
		StorageManager.getInstance().savePlayers();
	}

}
