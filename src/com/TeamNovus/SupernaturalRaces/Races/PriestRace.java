package com.TeamNovus.SupernaturalRaces.Races;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEntityEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerDamageEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerJoinRaceEvent;
import com.TeamNovus.SupernaturalRaces.Events.PlayerLeaveRaceEvent;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;
import com.TeamNovus.SupernaturalRaces.Spells.DemonicBlind;

public class PriestRace implements SNRace {

	@Override
	public String name() {
		return "Priest";
	}

	@Override
	public Integer maxPower() {
		return 100;
	}
	
	@Override
	public Integer powerIncrement() {
		return 10;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new DemonicBlind());
		return spells;
	}

	@Override
	public void onPlayerJoinRace(PlayerJoinRaceEvent event) {
		
	}

	@Override
	public void onPlayerLeaveRace(PlayerLeaveRaceEvent event) {
		
	}


	@Override
	public void onPlayerDamage(PlayerDamageEvent event) {		
		if(event.getCause().equals(DamageCause.FIRE_TICK)) {
			event.setDamage(event.getDamage()/2);
		}
	}
	
	@Override
	public void onPlayerDamageEntity(PlayerDamageEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
}
