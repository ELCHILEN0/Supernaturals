package com.TeamNovus.Supernaturals.Classes.Mage.Classes.Wizard.Powers;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Heal extends Power {
	private int amount;
	
	public Heal(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
	}
	
	public Heal(String name, String desc, Integer cooldown, Reagent reagent) {
		this(name, desc, cooldown, reagent, reagent);
	}
	
	public Heal setAmount(int amount) {
		this.amount = amount;
		
		return this;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		player.setHealth(player.getHealth() + amount);
		
		return false;
	}

}
