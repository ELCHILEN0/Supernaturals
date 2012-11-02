package com.TeamNovus.SupernaturalRaces.Race.Priest;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEvents;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class PriestRace implements SNRace {

	@Override
	public String name() {
		return "Priest";
	}

	@Override
	public Integer maxPower() {
		return 100;
	}
	
	@Override
	public Integer powerIncrement() {
		return 15;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new HolyBuff());
		spells.add(new HolyHeal());
		spells.add(new HolySummon());
		return spells;
	}

	@Override
	public List<SNEvents> events() {
		List<SNEvents> playerEvents = new ArrayList<SNEvents>();
		playerEvents.add(null);
		return playerEvents;
	}
}
