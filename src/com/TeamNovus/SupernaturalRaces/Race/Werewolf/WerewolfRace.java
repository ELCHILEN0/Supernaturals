package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
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
