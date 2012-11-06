package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Listeners.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class VampireRace implements Race {

	@Override
	public String name() {
		return "Vampire";
	}

	@Override
	public Integer maxPower() {
		return 750;
	}

	@Override
	public Integer powerIncrement() {
		return 35;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
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
