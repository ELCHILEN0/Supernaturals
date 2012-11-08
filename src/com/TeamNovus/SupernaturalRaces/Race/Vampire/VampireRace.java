package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class VampireRace implements Race {

	@Override
	public String name() {
		return "Vampire";
	}
	
	@Override
	public String info() {
		return "Gains 25 power during NIGHT 0 during DAY.  SPEED_EFFECT at NIGHT and a chance to burn during DAY.  Deals 120% damage and can make players BLEED during NIGHT and takes 300% damage from WOODEN_SWORDS.";
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
