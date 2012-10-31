package com.TeamNovus.SupernaturalRaces.Races;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class HumanRace implements SNRace {

	@Override
	public String name() {
		return "Human";
	}

	@Override
	public Integer maxPower() {
		return 0;
	}

	@Override
	public List<SNSpell> spells() {
		return new ArrayList<SNSpell>();
	}

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {	
		
	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {	
		
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {		
		
	}

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {	
		
	}

}
