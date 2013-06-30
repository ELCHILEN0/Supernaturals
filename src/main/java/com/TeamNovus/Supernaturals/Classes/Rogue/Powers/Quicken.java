package com.TeamNovus.Supernaturals.Classes.Rogue.Powers;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;
import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;
import com.TeamNovus.Supernaturals.Player.Class.Power;

public class Quicken extends Power {
	private Integer duration;
	private Integer amplifier;
	
	public Quicken(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		super(name, desc, cooldown, required, consume);
		
		duration = 60 * 20;
		amplifier = 1;
	}
	
	public Quicken setDuration(Integer duration) {
		this.duration = duration;
		
		return this;
	}
	
	public Quicken setAmplifier(Integer amplifier) {
		this.amplifier = amplifier;
		
		return this;
	}

	@Override
	public Boolean cast(SNPlayer player) {
		player.getEntity().addEffect(new Effect(EffectType.SPEED, duration, amplifier));
		
		return true;
	}

	

}
