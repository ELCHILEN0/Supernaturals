package com.TeamNovus.SupernaturalRaces.Race.Ranger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class RangerRace implements Race {

	@Override
	public String name() {
		return "Ranger";
	}

	@Override
	public ChatColor color() {
		return ChatColor.DARK_AQUA;
	}

	@Override
	public Integer maxPower() {
		return 500;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new ExplodingArrow());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		events.add(RangerEvents.class);
		return events;
	}

}
