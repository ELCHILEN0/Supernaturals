package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEvents;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class Vampire implements SNRace {

	@Override
	public String name() {
		return "Vampire";
	}

	@Override
	public Integer maxPower() {
		return 150;
	}

	@Override
	public Integer powerIncrement() {
		return 5;
	}

	@Override
	public List<SNSpell> spells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SNEvents> events() {
		List<SNEvents> playerEvents = new ArrayList<SNEvents>();
		playerEvents.add(null);
		return playerEvents;
	}

}
