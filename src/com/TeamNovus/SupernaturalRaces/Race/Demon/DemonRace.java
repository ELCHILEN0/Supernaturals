package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class DemonRace implements Race {

	@Override
	public String name() {
		return "Demon";
	}
	
	@Override
	public String info() {
		return "Gain 15 power in the NETHER, 10 during NIGHT and 0 during DAY.  Takes NO damage from FIRE and 125% damage in WATER.";
	}

	@Override
	public Integer maxPower() {
		return 1000;
	}
	
	@Override
	public Integer powerIncrement() {
		return 10;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new FireRing());
		spells.add(new Explode());
		spells.add(new ShockHeal());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		events.add(DemonicEvents.class);
		return events;
	}
}
