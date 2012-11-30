package com.TeamNovus.SupernaturalRaces.Race.Angel;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class AngelRace implements Race {

	@Override
	public String name() {
		return "Angel";
	}
	
	@Override
	public ChatColor color() {
		return ChatColor.DARK_PURPLE;
	}

	@Override
	public Integer maxPower() {
		return 1000;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new Soar());
		spells.add(new Luminatus());
		spells.add(new Blind());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> playerEvents = new ArrayList<Class<? extends SNEventListener>>();
		playerEvents.add(AngelicEvents.class);
		return playerEvents;
	}
}
