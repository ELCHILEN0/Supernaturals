package com.TeamNovus.SupernaturalRaces.Models;

import java.util.List;

import org.bukkit.ChatColor;



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
	 * The increment when gaining power
	 */
	Integer powerIncrement();
	
	/**
	 * The spells registered to the race
	 */
	List<Spell> spells();
		
	/**
	 * The events registered to the race
	 */
	List<Class<? extends SNEventListener>> events();
	
}
