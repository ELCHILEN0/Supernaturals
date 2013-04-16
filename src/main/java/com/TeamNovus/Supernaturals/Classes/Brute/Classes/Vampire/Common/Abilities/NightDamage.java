package com.TeamNovus.Supernaturals.Classes.Brute.Classes.Vampire.Common.Abilities;

import java.util.Random;

import com.TeamNovus.Supernaturals.Events.EntityDamageEntityEvent;
import com.TeamNovus.Supernaturals.Player.Class.Ability;

public class NightDamage extends Ability {
	private Integer chance;

	public NightDamage(String name, String desc, Integer chance) {
		super(name, desc, 0);

		this.chance = chance;
	}

	public void onEntityDamageEntity(EntityDamageEntityEvent event) {
		if(new Random().nextInt(101) <= chance) {	

			event.setDamage(event.getDamage() + 1);
		}
	}
}
