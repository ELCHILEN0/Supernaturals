package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Listeners.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class PriestRace implements Race {

	@Override
	public String name() {
		return "Priest";
	}

	@Override
	public Integer maxPower() {
		return 800;
	}
	
	@Override
	public Integer powerIncrement() {
		return 20;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
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
