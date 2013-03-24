package com.TeamNovus.Supernaturals.Player.Class;

import com.TeamNovus.Supernaturals.Models.Reagent;
import com.TeamNovus.Supernaturals.Player.SNPlayer;

public abstract class Power {
	private String name;
	private String desc;
	private Integer cooldown;
	private Reagent required;
	private Reagent consume;
	
	public Power(String name, String desc, Integer cooldown, Reagent required, Reagent consume) {
		this.name = name;
		this.desc = desc;
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
	
	public abstract Boolean cast(SNPlayer player);
}
