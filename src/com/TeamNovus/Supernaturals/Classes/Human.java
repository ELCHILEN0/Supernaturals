package com.TeamNovus.Supernaturals.Classes;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Rogue.Rogue;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Human extends SNClass {

	public Human() {
		super("Human", ChatColor.WHITE, 0, null);
		addJoinableClass(0, new Rogue());
	}

}
