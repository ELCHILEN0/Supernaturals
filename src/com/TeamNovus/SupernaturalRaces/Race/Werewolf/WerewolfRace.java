package com.TeamNovus.SupernaturalRaces.Race.Werewolf;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.Race;
import com.TeamNovus.SupernaturalRaces.Character.Spell;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class WerewolfRace implements Race {
	
	@Override
	public String name() {
		return "Werewolf";
	}
	
	@Override
	public ChatColor color() {
		return ChatColor.GRAY;
	}

	@Override
	public Integer maxPower() {
		return 750;
	}

	@Override
	public List<Spell> spells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(new Train());
		spells.add(new Scent());
		spells.add(new Haste());
		return spells;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		List<Class<? extends SNEventListener>> playerEvents = new ArrayList<Class<? extends SNEventListener>>();
		playerEvents.add(WerewolfEvents.class);
		return playerEvents;
	}
}
