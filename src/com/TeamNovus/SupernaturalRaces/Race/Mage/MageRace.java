package com.TeamNovus.SupernaturalRaces.Race.Mage;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class MageRace implements Race {

	@Override
	public String name() {
		return "Mage";
	}

	@Override
	public Integer maxPower() {
		return 500;
	}

	@Override
	public Integer powerIncrement() {
		return 10;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new IceWalk());
		spells.add(new Freeze());
		spells.add(new Combust());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		return events;
	}

}
