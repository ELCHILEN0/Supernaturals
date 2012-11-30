package com.TeamNovus.SupernaturalRaces.Race.Human;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class HumanRace implements Race {

	@Override
	public String name() {
		return "Human";
	}

	@Override
	public ChatColor color() {
		return ChatColor.GREEN;
	}
	
	@Override
	public Integer maxPower() {
		return 0;
	}
	
	@Override
	public List<Spell> spells() {
		return new ArrayList<Spell>();
	}
	
	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		return events;
	}
}
