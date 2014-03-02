package com.TeamNovus.Supernaturals.Tasks;

import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class EntityTickTask implements Runnable {
	public void run() {		
		for(SNPlayer p : SNPlayer.getOnlinePlayers()) {
			p.tick();
			p.updateGUI();
		}
		
		for(SNEntity e : SNEntity.getAllEntities()) {
			e.tick();
		}
	}

}
