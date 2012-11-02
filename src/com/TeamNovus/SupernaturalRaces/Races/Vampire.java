package com.TeamNovus.SupernaturalRaces.Races;

import java.util.List;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Vampire implements SNRace {

	@Override
	public String name() {
		return "Vampire";
	}

	@Override
	public Integer maxPower() {
		return 150;
	}

	@Override
	public Integer powerIncrement() {
		return 5;
	}

	@Override
	public List<SNSpell> spells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}

}
