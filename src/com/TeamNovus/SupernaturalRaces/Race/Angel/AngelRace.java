package com.TeamNovus.SupernaturalRaces.Race.Angel;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class AngelRace implements Race {

	@Override
	public String name() {
		return "Angel";
	}
	
	@Override
	public String info() {
		return "Gain 20 power during the DAY, 5 during NIGHT.  Take 75% damage during DAY, 125% during NIGHT and NO damage from FALLING.";
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
		spells.add(new Soar());
		spells.add(new Luminatus());
		spells.add(new Blind());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> playerEvents = new ArrayList<Class<? extends SNEventListener>>();
		playerEvents.add(AngelicEvents.class);
		return playerEvents;
	}
}
