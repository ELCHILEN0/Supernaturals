package com.TeamNovus.Supernaturals.Tasks;

import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Statistics.Cooldown;

public class CooldownTask implements Runnable {

	public void run() {
		for(SNPlayer p : SNPlayer.getOnlinePlayers()) {			
			for (Cooldown cooldown : p.getCooldowns()) {
				cooldown.setRemaining(cooldown.getRemaining() - 1);
				cooldown.save();
				
				// If there is nothing left in the cooldown we can delete it...
				if(cooldown.getRemaining() <= 0) {
					cooldown.delete();
					
					// TODO: Power Refresh Event
				}
			}
		}		
	}

}
