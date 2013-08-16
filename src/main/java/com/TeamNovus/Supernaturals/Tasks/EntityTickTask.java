package com.TeamNovus.Supernaturals.Tasks;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Entity.SNEntity;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public class EntityTickTask implements Runnable {

	public void run() {
		List<SNEntity> unusedEntities = new ArrayList<SNEntity>();
		
		for(SNPlayer p : SNPlayers.i.getOnlinePlayers()) {
			p.tick();
		}
		
		for(SNEntity e : SNEntities.i.getEntites()) {
			e.tick();
			
			if(e.getEffects().size() == 0) {
				unusedEntities.add(e);
			}
		}
		
		for(SNEntity e : unusedEntities) {
			SNEntities.i.detach(e);
		}
	}

}
