package com.TeamNovus.Supernaturals.Player.Class;

import com.TeamNovus.Supernaturals.Entity.Effect;

public class Ability extends Effect {
	private String name;
	private String desc;
	private Integer amplifier;
	
	public Ability(String name, String desc, Integer amplifier) {
		super();
		
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
