package com.TeamNovus.Supernaturals.Tasks;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class ManaRegainTask implements Runnable {

	@Override
	public void run() {
		for(SNPlayer p : SNPlayers.i.getOnlinePlayers()) {
			p.setMana(p.getMana() + 10, true);
		}
	}

}