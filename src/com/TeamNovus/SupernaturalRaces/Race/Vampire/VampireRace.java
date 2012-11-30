package com.TeamNovus.SupernaturalRaces.Race.Vampire;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class VampireRace implements Race {

	@Override
	public String name() {
		return "Vampire";
	}
	
	@Override
	public ChatColor color() {
		return ChatColor.DARK_GRAY;
	}

	@Override
	public Integer maxPower() {
		return 750;
	}
	
	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new Vanish());
		spells.add(new Strenghten());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		events.add(VampireEvents.class);
		return events;
	}

}
