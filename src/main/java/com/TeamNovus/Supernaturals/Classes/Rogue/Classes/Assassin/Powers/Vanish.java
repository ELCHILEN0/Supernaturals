package com.TeamNovus.Supernaturals.Classes.Rogue.Classes.Assassin.Powers;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Vanish extends Power {
	private Integer duration;
	
	public Vanish(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		duration = 30;
	}

	public Vanish setDuration(Integer duration) {
		this.duration = duration;
		
		return this;
	}
	
	@Override
	public Boolean cast(SNPlayer player) {
		player.getEntity().addEffect(new Effect(EffectType.INVISIBILITY, duration));
		
		return true;
	}
}
