package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class WerewolfRace implements Race {
	
	@Override
	public String name() {
		return "Werewolf";
	}

	@Override
	public Integer maxPower() {
		return 750;
	}
	
	@Override
	public Integer powerIncrement() {
		return 30;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new Train());
		spells.add(new Scent());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> playerEvents = new ArrayList<Class<? extends SNEventListener>>();
		playerEvents.add(WerewolfEvents.class);
		return playerEvents;
	}
}
