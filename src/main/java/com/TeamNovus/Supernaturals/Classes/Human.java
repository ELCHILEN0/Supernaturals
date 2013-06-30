package com.TeamNovus.Supernaturals.Classes;

import org.bukkit.ChatColor;

import com.TeamNovus.Supernaturals.Classes.Barbarian.Barbarian;
import com.TeamNovus.Supernaturals.Classes.Mage.Mage;
import com.TeamNovus.Supernaturals.Classes.Rogue.Rogue;
import com.TeamNovus.Supernaturals.Classes.Warrior.Warrior;
import com.TeamNovus.Supernaturals.Player.SNClass;

public class Human extends SNClass {

	public Human() {
 		super("Human", ChatColor.GREEN, 1);
 		
 		addJoinableClass(1, new Barbarian(this));
 		addJoinableClass(1, new Mage(this));
 		addJoinableClass(1, new Rogue(this));
 		addJoinableClass(1, new Warrior(this));
	}

}
