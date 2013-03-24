package com.TeamNovus.Supernaturals.Classes;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Brute.Brute;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Human extends SNClass {

	public Human() {
 		super("Human", ChatColor.GREEN, 1);
 		
 		addJoinableClass(1, new Brute(this));
	}

}
