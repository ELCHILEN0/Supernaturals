package com.TeamNovus.Supernaturals.Tasks;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.Supernaturals.SNEntities;
import com.TeamNovus.Supernaturals.Entity.SNEntity;

public class EntityTickTask implements Runnable {

	@Override
	public void run() {
		List<SNEntity> toRemove = new ArrayList<SNEntity>();
		
		for(SNEntity e : SNEntities.i.getEntites()) {
			if(e.isAlive())
				e.tick();
			
			if(e.getEffects().size() == 0) {
				toRemove.add(e);
			}
		}
		
		for(SNEntity e : toRemove) {
			SNEntities.i.detach(e);
		}
	}

}
