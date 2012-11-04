package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class VampireRace implements SNRace {

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
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new Vanish());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		events.add(VampireEvents.class);
		return events;
	}

}
