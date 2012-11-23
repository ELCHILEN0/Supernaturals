package com.TeamNovus.SupernaturalRaces.Race.Necromancer;

import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Models.Race;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;
import com.TeamNovus.SupernaturalRaces.Models.Spell;

public class NecromancerRace implements Race {

	@Override
	public String name() {
		return "Necromancer";
	}

	@Override
	public ChatColor color() {
		return ChatColor.BLACK;
	}

	@Override
	public Integer maxPower() {
		return 1500;
	}

	@Override
	public List<Spell> spells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends SNEventListener>> events() {
		// TODO Auto-generated method stub
		return null;
	}

}
