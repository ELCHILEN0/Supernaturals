package com.TeamNovus.SupernaturalRaces.Character;

import java.util.ArrayList;

import com.TeamNovus.SupernaturalRaces.SupernaturalRaces;
import com.TeamNovus.SupernaturalRaces.Models.SNAttributes;

public class SNPlayer extends SNAttributes {
	private String race = "Human";
	private Integer power = 0;
	private Integer boundSpellId = 0;
	private ArrayList<SNEffect> effects = new ArrayList<SNEffect>();
	
	public String getRace() {
		return race;
	}
	
	public Race getPlayerRace() {
		return SupernaturalRaces.getRaceManager().getBestRace(race);
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public void setRace(Race race) {
		this.race = race.name();
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	public void setBoundSpellId(Integer boundSpellId) {
		this.boundSpellId = boundSpellId;
	}
	
	public Integer getBoundSpellId() {
		return boundSpellId;
	}
	
	public void addEffect(SNEffect effect) {
		effects.add(effect);
	}
	
	public void removeEffects(SNEffect effect) {
		for(SNEffect e : effects) {

		}
	}
	
	public ArrayList<SNEffect> getEffects() {
		return effects;
	}
}
