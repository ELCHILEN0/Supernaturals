package com.TeamNovus.SupernaturalRaces.Race.Human;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Character.SNEffect;
import com.TeamNovus.SupernaturalRaces.Character.SNEffect.SNEffectType;
import com.TeamNovus.SupernaturalRaces.Character.SNRace;
import com.TeamNovus.SupernaturalRaces.Character.SNSpell;
import com.TeamNovus.SupernaturalRaces.Effects.BleedEffect;
import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public class HumanRace implements SNRace {

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
	public ArrayList<SNSpell> spells() {
		ArrayList<SNSpell> spells = new ArrayList<SNSpell>();
		return spells;
	}
	
	@Override
	public ArrayList<Class<? extends SNEventListener>> events() {
		ArrayList<Class<? extends SNEventListener>> events = new ArrayList<Class<? extends SNEventListener>>();
		return events;
	}

	@Override
	public ArrayList<SNEffect> effects() {
		ArrayList<SNEffect> effects = new ArrayList<SNEffect>();
		effects.add(new SNEffect(5 * 20, 0, BleedEffect.class, SNEffectType.BLEED));
		return effects;
	}
}
