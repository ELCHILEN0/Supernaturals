package com.TeamNovus.SupernaturalRaces.Race.Angel;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEvents;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class AngelRace implements SNRace {

	@Override
	public String name() {
		return "Angel";
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
		spells.add(new AngelicJump());
		spells.add(new AngelicVanish());
		return spells;
	}

	@Override
	public List<SNEvents> events() {
		List<SNEvents> playerEvents = new ArrayList<SNEvents>();
		playerEvents.add(new AngelicEvents());
		return playerEvents;
	}
}
