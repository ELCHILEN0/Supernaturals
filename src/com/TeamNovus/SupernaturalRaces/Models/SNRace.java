package com.TeamNovus.SupernaturalRaces.Models;

import java.util.List;


public interface SNRace {
	/**
	 * The name of the race
	 */
	String name();
	
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
	List<SNSpell> spells();
		
	/**
	 * The events registered to the race
	 */
	List<Class<? extends SNEventListener>> events();
	
}
