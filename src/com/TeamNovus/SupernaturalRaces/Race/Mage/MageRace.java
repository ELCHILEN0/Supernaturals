package com.TeamNovus.SupernaturalRaces.Race.Mage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class MageRace implements Race {

	@Override
	public String name() {
		return "Mage";
	}
	
	@Override
	public ChatColor color() {
		return ChatColor.DARK_GREEN;
	}

	@Override
	public Integer maxPower() {
		return 500;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new IceWalk());
		spells.add(new Freeze());
		spells.add(new Combust());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		return events;
	}

}
