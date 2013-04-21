package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Werewolf.Common.Abilities;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class Claws extends Ability {

	public Claws(String name, String desc, Integer amplifier) {
		super(name, desc, amplifier);
	}

	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		event.setDamage(event.getDamage() + 1);
	}
	
}
