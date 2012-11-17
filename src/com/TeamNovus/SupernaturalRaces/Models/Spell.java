package com.TeamNovus.SupernaturalRaces.Models;

import org.bukkit.entity.Player;

import com.TeamNovus.SupernaturalRaces.Util.Reagent;

public interface Spell {	
	/**
	 * The name of the spell.
	 */
	String name();
	
	/**
	 * The required to cast the spell.
	 */
	Reagent required();
	
	/**
	 * The reagent to consume.
	 * By default you should use reagent()
	 */
	Reagent consume();
	
	/**
	 * Handle spell execution.
	 * Targets can be acquired and manipulated.
	 */
	Boolean execute(Player sender);
}
