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
	public String[] info() {
		String[] info = {  };
		info[0] = "Gains 25 power during NIGHT 0 during DAY.";
		info[1] = "Deals 125% damage during NIGHT and takes 200% damage from IRON_SWORDS.";
		return info;
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
