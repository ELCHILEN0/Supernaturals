package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

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
		return 15;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new Renew());
		spells.add(new Cure());
		spells.add(new HolySpirit());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> playerEvents = new ArrayList<Class<? extends SNEventListener>>();
		playerEvents.add(PriestEvents.class);
		return playerEvents;
	}
}
