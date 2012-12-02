package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public interface SNRace {
	/**
	 * The name of the race
	 */
	String name();
	
	/**
	 * The color of the race
	 */
	ChatColor color();
	
	/**
	 * The max power of the race
	 */
	Integer maxPower();
	
	/**
	 * The spells registered to the race
	 */
	ArrayList<SNSpell> spells();
		
	/**
	 * The events registered to the race
	 */
	@Deprecated
	ArrayList<Class<? extends SNEventListener>> events();
	ArrayList<SNEffect> effects();
}
