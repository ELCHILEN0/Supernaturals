package com.TeamNovus.Supernaturals.Player.Class;

import org.bukkit.entity.Player;

import com.TeamNovus.Supernaturals.Models.Reagent;

public abstract class Power {
	private String name;
	private String desc;
	private Integer cooldown;
	private Reagent required;
	private Reagent consume;
	private Double amplifier;
	
	public Power(String name, String desc, Double amplifier, Integer cooldown, Reagent required, Reagent consume) {
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
	
	public Double getAmplifier() {
		return amplifier;
	}
	
	public abstract Boolean cast(Player sender);
}
