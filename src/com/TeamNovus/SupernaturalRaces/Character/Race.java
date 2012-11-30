package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.TeamNovus.SupernaturalRaces.Models.SNEventListener;

public interface Race {
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
	List<Spell> spells();
		
	/**
	 * The events registered to the race
	 */
	@Deprecated
	List<Class<? extends SNEventListener>> events();
	
	/**
	 * The effects registered to the race
	 */
	ArrayList<SNEffect> effects();
}
