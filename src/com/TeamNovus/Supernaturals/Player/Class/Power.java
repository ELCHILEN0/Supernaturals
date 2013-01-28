package com.TeamNovus.Supernaturals.Player.Class;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public abstract class Power {
	private String name;
	private String desc;
	private Integer amplifier;
	private Integer cooldown;
	private Reagent required;
	private Reagent consume;
	
	public Power(String name, String desc, Integer amplifier, Integer cooldown, Reagent required, Reagent consume) {
		this.name = name;
		this.desc = desc;
		this.amplifier = amplifier;
		this.cooldown = cooldown;
		this.required = required;
		this.consume = consume;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public Integer getCooldown() {
		return cooldown;
	}
	
	public Reagent getRequired() {
		return required;
	}
	
	public Reagent getConsume() {
		return consume;
	}
	
	public Integer getAmplifier() {
		return amplifier;
	}
	
	public abstract Boolean cast(SNPlayer player);
}
