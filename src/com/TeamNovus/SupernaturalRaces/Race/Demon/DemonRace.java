package com.TeamNovus.SupernaturalRaces.Race.Demon;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEvents;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class DemonRace implements SNRace {

	@Override
	public String name() {
		return "Demon";
	}

	@Override
	public Integer maxPower() {
		return 100;
	}
	
	@Override
	public Integer powerIncrement() {
		return 10;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new FireRing());
		spells.add(new Explode());
		spells.add(new ShockHeal());
		return spells;
	}

	@Override
	public List<SNEvents> events() {
		List<SNEvents> events = new ArrayList<SNEvents>();
		events.add(new DemonicEvents());
		return events;
	}
}
