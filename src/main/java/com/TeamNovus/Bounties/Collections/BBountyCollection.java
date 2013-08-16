package com.TeamNovus.Bounties.Collections;

import java.util.Collection;
import java.util.HashMap;

import com.TeamNovus.Bounties.Player.BBounty;

public class BBountyCollection {
	private HashMap<String, BBounty> entities = new HashMap<String, BBounty>();

	public Collection<BBounty> getBBounties() {
		return entities.values();
	}
	
	public BBounty get(String name) {
		return entities.get(name);
	}
	
	public BBounty attach(BBounty b) {
		return entities.put(b.getTarget(), b);
	}
	
	public void detach(BBounty b) {
		entities.remove(b);
	}
	
	public boolean attached(BBounty b) {		
		return entities.containsKey(b.getTarget());
	}
	
	public boolean detached(BBounty b) {
		return !(attached(b));
	}
	
}
