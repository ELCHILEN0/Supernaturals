package com.TeamNovus.SupernaturalRaces.Race.Human;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class HumanRace implements SNRace {

	@Override
	public String name() {
		return "Human";
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
	public List<SNSpell> spells() {
		return new ArrayList<SNSpell>();
	}
	
	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		return events;
	}
}
