package com.TeamNovus.SupernaturalRaces.Models;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public interface SNSpell {
	
	/**
	 * The spells name.
	 */
	String name();
	
	/**
	 * The spells description.
	 */
	String desc();
	
	/**
	 * The click actions required to activate the spell.
	 */
	List<Action> actions();
	
	/**
	 * The materials the spell is bound to.
	 */
	List<Material> bindings();
	
	/**
	 * The power required to cast the spell.
	 */
	Integer power();
	
	/**
	 * Consume the item when cast.
	 */
	Boolean consume();

	/**
	 * The code to execute on the spell.
	 */
	void execute(Player sender);
}
