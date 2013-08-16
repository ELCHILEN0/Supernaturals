package com.TeamNovus.Supernaturals.Tasks;

import java.util.Iterator;

import com.TeamNovus.Supernaturals.SNPlayers;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Statistics.Cooldown;

public class CooldownTask implements Runnable {

	public void run() {
		for(SNPlayer p : SNPlayers.i.getOnlinePlayers()) {
			Iterator<Cooldown> iterator = p.getCooldowns().iterator();
			while(iterator.hasNext()) {
				Cooldown cooldown = iterator.next();
				
				cooldown.setRemainingTicks(cooldown.getRemainingTicks() - 1);
				
				if(cooldown.getRemainingTicks() <= 0) {
					iterator.remove();
					
					// TODO: Power Refresh Event
				}
			}
		}		
	}

}
