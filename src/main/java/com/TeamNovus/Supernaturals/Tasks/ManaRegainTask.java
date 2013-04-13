package com.TeamNovus.Supernaturals.Tasks;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class ManaRegainTask implements Runnable {

	public void run() {
		for(SNPlayer p : SNPlayers.i.getOnlinePlayers()) {
			p.setMana(p.getMana() + 2, true);
		}
	}

}
