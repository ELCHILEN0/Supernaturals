package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEvents;
import com.TeamNovus.SupernaturalRaces.Models.SNRace;
import com.TeamNovus.SupernaturalRaces.Models.SNSpell;

public class WerewolfRace implements SNRace {
	
	@Override
	public String name() {
		return "Werewolf";
	}

	@Override
	public Integer maxPower() {
		return 75;
	}
	
	@Override
	public Integer powerIncrement() {
		return 20;
	}

	@Override
	public List<SNSpell> spells() {
		List<SNSpell> spells = new ArrayList<SNSpell>();
		spells.add(new Howl());
		spells.add(new Train());
		spells.add(new Scent());
		return spells;
	}

	@Override
	public List<SNEvents> events() {
		List<SNEvents> playerEvents = new ArrayList<SNEvents>();
		playerEvents.add(new WerewolfEvents());
		return playerEvents;
	}
}
