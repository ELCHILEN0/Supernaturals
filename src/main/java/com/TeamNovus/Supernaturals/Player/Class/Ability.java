package com.TeamNovus.Supernaturals.Player.Class;

import com.TeamNovus.Supernaturals.Custom.Effect.Effect;
import com.TeamNovus.Supernaturals.Custom.Effect.EffectType;

public class Ability extends Effect {
	private String name;
	private String desc;
	
	public Ability(EffectType type, String name, String desc) {
		super(type);
		
		this.name = name;
		this.desc = desc;		
	}
	
	public Ability(EffectType type, String name, String desc, Integer amplifier) {
		this(type, name, desc);
		
		setAmplifier(amplifier);
	}

	public Ability(EffectType type, String name, String desc, Integer amplifier, Integer period) {
		this(type, name, desc);
		
		setAmplifier(amplifier);
		setPeriod(period);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
}
