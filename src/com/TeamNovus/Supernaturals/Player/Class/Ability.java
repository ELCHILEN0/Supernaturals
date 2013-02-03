package com.TeamNovus.Supernaturals.Player.Class;

import com.TeamNovus.Supernaturals.Entity.Effects.BaseEffect;

public class Ability extends BaseEffect {
	private String name;
	private String desc;
	protected Integer amplifier;
	
	public Ability(String name, String desc, Integer amplifier) {
		this.name = name;
		this.desc = desc;
		this.amplifier = amplifier;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public Integer getAmplifier() {
		return amplifier;
	}
}
