package com.TeamNovus.SupernaturalRaces.Race.Human;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class HumanRace implements Race {

	@Override
	public String name() {
		return "Human";
	}

	@Override
	public String info() {
		return "Just regular Steve.  No special powers or weaknesses.";
	}
	
	@Override
	public Integer maxPower() {
		return 0;
	}

	@Override
	public Integer powerIncrement() {
		return 0;
	}
	
	@Override
	public List<Spell> spells() {
		return new ArrayList<Spell>();
	}
	
	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		return events;
	}
}
